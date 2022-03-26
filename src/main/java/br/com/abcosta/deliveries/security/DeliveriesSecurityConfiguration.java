package br.com.abcosta.deliveries.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class DeliveriesSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DeliveriesEntryPoint entryPoint;
	
	public void configure(HttpSecurity httpSecurity) throws Exception {
		System.out.println(">>>>>>> SETUP DA CONFIGURAÇÃO DE SEGURANÇA");
		
		httpSecurity.csrf().disable()
							.exceptionHandling()
							.authenticationEntryPoint(entryPoint)
							.and()
							.authorizeRequests()
							.antMatchers(HttpMethod.GET, "/entregadores").permitAll()
							.antMatchers(HttpMethod.POST, "/login*").permitAll()
							.anyRequest().authenticated().and().cors();
		
		httpSecurity.addFilterBefore(new DeliveriesFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
