package br.com.abcosta.deliveries.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.abcosta.deliveries.model.DeliveryPerson;

public interface DeliveryPersonDAO extends CrudRepository<DeliveryPerson, Integer> {

	public DeliveryPerson findByEmail(String email);
}
