package br.com.abcosta.deliveries.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.abcosta.deliveries.model.DeliveryMan;
import br.com.abcosta.deliveries.security.Token;
import br.com.abcosta.deliveries.services.IDeliveryManService;

@RestController
public class LoginController {
	
	@Autowired
	private IDeliveryManService services;
	
	@PostMapping("login")
	public ResponseEntity<Token> login(@RequestBody DeliveryMan body) {
		Token token = services.cretaeLoginToken(body);
		if(token != null) {
			return ResponseEntity.ok(token);
		}
		
		return ResponseEntity.status(401).build();
	}

//	@GetMapping("/testelogin")
//	public ResponseEntity<Token> fakeLogin() {
//		DeliveryMan user = new DeliveryMan();
//		user.setName("user");
//		user.setEmail("user@email.com");
//		user.setPassword("user@01");
//		
//		Token token = new Token(TokenUtil.createToken(user));
//		System.out.println(">>>>>>> LOGIN CONTROLLER");
//		return ResponseEntity.ok(token);
//	}
	
}
