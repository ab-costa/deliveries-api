package br.com.abcosta.deliveries.services;

import br.com.abcosta.deliveries.model.DeliveryMan;
import br.com.abcosta.deliveries.security.Token;

public interface IDeliveryManService {
	
	public Token cretaeLoginToken(DeliveryMan deliveryMan);
}
