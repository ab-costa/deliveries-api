package br.com.abcosta.deliveries.services;

import br.com.abcosta.deliveries.model.DeliveryPerson;
import br.com.abcosta.deliveries.security.Token;

public interface IDeliveryPersonService {
	
	public Token cretaeLoginToken(DeliveryPerson deliveryPerson);
	public DeliveryPerson createProfile(DeliveryPerson deliveryPerson);
	public DeliveryPerson getProfile();
	public void deleteProfile();
	public boolean assignToOrder();
	public boolean cancelOrderAssignment();
}
