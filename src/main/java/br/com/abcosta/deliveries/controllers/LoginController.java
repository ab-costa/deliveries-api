package br.com.abcosta.deliveries.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.abcosta.deliveries.model.DeliveryPerson;
import br.com.abcosta.deliveries.security.Token;
import br.com.abcosta.deliveries.services.IDeliveryPersonService;
import br.com.abcosta.deliveries.services.util.Message;

@RestController
public class LoginController {
	
	@Autowired
	private IDeliveryPersonService services;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody DeliveryPerson body) {
		
		try {
			if(body.getEmail() == null) {
				throw new RuntimeException("O campo email é obrigatório");
			}
			
			if(body.getPassword() == null) {
				throw new RuntimeException("O campo senha é obrigatório");
			}
			
			Token token = services.cretaeLoginToken(body);
			if(token != null) {
				return ResponseEntity.ok(token);
			}
			
			return ResponseEntity.status(401).build();
			
		} catch (Exception exception) {
			
			return ResponseEntity.badRequest().body(new Message(exception.getMessage()));
		}
	}	
}
