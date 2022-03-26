package br.com.abcosta.deliveries.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.abcosta.deliveries.model.DeliveryMan;

public interface DeliveryManDAO extends CrudRepository<DeliveryMan, Integer> {

}
