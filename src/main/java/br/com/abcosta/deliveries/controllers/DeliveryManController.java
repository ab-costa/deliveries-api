package br.com.abcosta.deliveries.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.abcosta.deliveries.dao.DeliveryManDAO;
import br.com.abcosta.deliveries.model.DeliveryMan;

@RestController
public class DeliveryManController {

	@Autowired
	private DeliveryManDAO dao;
	
	
	@GetMapping("/entregadores")
	public List<DeliveryMan> couriersList() {
		return (List<DeliveryMan>)dao.findAll();
	}
	
	@GetMapping("/entregadores/{id}")
	public Optional<DeliveryMan> getDeliveryMan(@PathVariable Integer id) {
		return dao.findById(id);
	}
	
	@PostMapping("/entregadores")
	public DeliveryMan createDeliveryMan(@RequestBody DeliveryMan deliveryMan) {
		return dao.save(deliveryMan);
	}
	
	@DeleteMapping("/entregadores/{id}")
	public void deleteDeliveryMan(@PathVariable Integer id) {
		dao.deleteById(id);
	}
}
