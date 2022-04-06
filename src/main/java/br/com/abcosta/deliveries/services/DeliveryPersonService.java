package br.com.abcosta.deliveries.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.abcosta.deliveries.dao.DeliveryPersonDAO;
import br.com.abcosta.deliveries.model.DeliveryPerson;
import br.com.abcosta.deliveries.security.DeliveriesCrypto;
import br.com.abcosta.deliveries.security.Token;
import br.com.abcosta.deliveries.security.TokenUtil;

@Component
public class DeliveryPersonService implements IDeliveryPersonService {

	@Autowired
	DeliveryPersonDAO dao;
	
	@Override
	public Token cretaeLoginToken(DeliveryPerson deliveryPerson) {
		DeliveryPerson user = new DeliveryPerson();
		user = dao.findByEmail(deliveryPerson.getEmail());
		
		try {
			System.out.println(">>>>>>> CLASSE DELIVERY MAN SERVICE | METHOD CREATE LOGIN TOKEN");
			System.out.println(">>>>>>> DEBUG: " + DeliveriesCrypto.encrypt(deliveryPerson.getPassword()));
			if(user != null && user.getPassword().equals(deliveryPerson.getPassword())) {
				Token token = new Token(TokenUtil.createToken(user));
				return token;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return null;
	}

	@Override
	public DeliveryPerson createProfile(DeliveryPerson deliveryPerson) {
		// TODO Auto-generated method stub
		System.out.println(">>>>> CREATE PROFILE");
		DeliveryPerson newDeliveryPerson = new DeliveryPerson();
		DeliveryPerson teste = dao.findByEmail(deliveryPerson.getEmail());
		
		try {
			newDeliveryPerson.setName(deliveryPerson.getName());
			System.out.println(newDeliveryPerson.getName());
			
			if(teste != null) {
				throw new RuntimeException("Email já cadastrado");
			}
			
			newDeliveryPerson.setEmail(deliveryPerson.getEmail());
			System.out.println(newDeliveryPerson.getEmail());
			newDeliveryPerson.setPassword(DeliveriesCrypto.encrypt(deliveryPerson.getPassword()));
			System.out.println(newDeliveryPerson.getPassword());
			
			dao.save(newDeliveryPerson);
			
			return newDeliveryPerson;
		} catch (Exception exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

	@Override
	public DeliveryPerson getProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProfile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean assignToOrder() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelOrderAssignment() {
		// TODO Auto-generated method stub
		return false;
	}
}
