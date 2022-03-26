package br.com.abcosta.deliveries.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
