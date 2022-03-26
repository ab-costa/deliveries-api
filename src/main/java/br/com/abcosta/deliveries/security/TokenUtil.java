package br.com.abcosta.deliveries.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import br.com.abcosta.deliveries.model.DeliveryMan;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class TokenUtil {
	
	private static final int SEGUNDOS = 1000;
	private static final int MINUTOS = 60 * SEGUNDOS;
	private static final int HORAS = 60 * MINUTOS;
	private static final int DIAS = 24 * HORAS;

	private static final String HEADER = "Authorization";
	private static final String PREFIX = "Bearer ";
	private static final long EXPIRATION = 2 * MINUTOS;
	private static final String SECRET_KEY = "83b483a36193c3ce03c8cec39df0db8e";
	private static final String ISSUER = "83b483a36193c3ce03c8cec39df0db8e";


	public static String createToken(DeliveryMan deliveryMan) {
		System.out.println(">>>>>>> TOKEN UTIL CLASS | CREATE TOKEN METHOD");
		Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
		
		String token = Jwts.builder().setSubject(deliveryMan.getName())
										.setIssuer(ISSUER)
										.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
										.signWith(secretKey, SignatureAlgorithm.HS256)
										.compact();
		
		System.out.println("TOKEN: " + token);
		
		return PREFIX + token;
	}
	
	private static boolean isExpiratioDateValid(Date expirationDate) {
		return expirationDate.after(new Date(System.currentTimeMillis()));
	}
	
	private static boolean isIssuerValid(String issuer) {
		return issuer.equals(ISSUER);
	}
	
	private static boolean isSubjectValid(String subject) {
		return subject != null && subject.length() > 0;
	}
	
	public static Authentication validateToken(HttpServletRequest request) {
		
		String token = request.getHeader(HEADER);
		
		token = token.replace(PREFIX, "");
		
		Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes())
												.build()
												.parseClaimsJws(token);
		
		Date expirationDate = jwsClaims.getBody().getExpiration();
		String issuer = jwsClaims.getBody().getIssuer();
		String name = jwsClaims.getBody().getSubject();
		
		if(isExpiratioDateValid(expirationDate) && isIssuerValid(issuer) && isSubjectValid(name)) {
			return new UsernamePasswordAuthenticationToken(name, null, Collections.emptyList());
		}
		
		return null;
	}
}
