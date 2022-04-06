package br.com.abcosta.deliveries.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.abcosta.deliveries.dao.DeliveryPersonDAO;
import br.com.abcosta.deliveries.model.DeliveryPerson;
import br.com.abcosta.deliveries.services.DeliveryPersonService;
import br.com.abcosta.deliveries.services.util.Message;

@RestController
public class DeliveryPersonController {

	@Autowired
	private DeliveryPersonDAO dao;
	
	@Autowired
	private DeliveryPersonService services;
	
	
	@GetMapping("/admin/entregadores")
	public List<DeliveryPerson> couriersList() {
		return (List<DeliveryPerson>)dao.findAll();
	}
	
	@GetMapping("/admin/entregadores/{id}")
	public Optional<DeliveryPerson> getDeliveryMan(@PathVariable Integer id) {
		return dao.findById(id);
	}
	
	@PostMapping("/admin/entregadores")
	public DeliveryPerson createDeliveryMan(@RequestBody DeliveryPerson deliveryPerson) {
		return dao.save(deliveryPerson);
	}
	
	@DeleteMapping("/admin/entregadores/{id}")
	public void deleteDeliveryPerson(@PathVariable Integer id) {
		dao.deleteById(id);
	}
	
	@PostMapping("/entregadoresteste")
	public ResponseEntity<?> createNewDeliveryPerson (@RequestBody DeliveryPerson body) {
		System.out.println(">>>>> CREATE DELIVERY PERSON");
		
		try {
			DeliveryPerson newDeliveryPerson = services.createProfile(body);
			System.out.println(newDeliveryPerson);
			
			if(newDeliveryPerson != null) {
				return ResponseEntity.ok(newDeliveryPerson);
			}
			
			return ResponseEntity.notFound().build();
			
		} catch (Exception exception) {
			
			return ResponseEntity.badRequest().body(new Message(exception.getMessage()));
		}
	}
}
