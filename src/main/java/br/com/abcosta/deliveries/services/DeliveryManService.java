package br.com.abcosta.deliveries.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.abcosta.deliveries.dao.DeliveryManDAO;
import br.com.abcosta.deliveries.model.DeliveryMan;
import br.com.abcosta.deliveries.security.Token;
import br.com.abcosta.deliveries.security.TokenUtil;

@Component
public class DeliveryManService implements IDeliveryManService {

	@Autowired
	DeliveryManDAO dao;
	
	@Override
	public Token cretaeLoginToken(DeliveryMan body) {
		DeliveryMan user = new DeliveryMan();
		user = dao.findByEmail(body.getEmail());
		
		if(user != null && user.getPassword().equals(body.getPassword())) {
			Token token = new Token(TokenUtil.createToken(user));
			return token;
		}
		
		return null;
	}

}
