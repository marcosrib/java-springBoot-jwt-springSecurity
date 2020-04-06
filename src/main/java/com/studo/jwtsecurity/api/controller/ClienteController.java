package com.studo.jwtsecurity.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.studo.jwtsecurity.domain.entity.ClienteEntity;
import com.studo.jwtsecurity.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/api/services")
public class ClienteController {
	
	@Autowired
    private ClienteRepository repository;
	
	@GetMapping("clientes/{id}")
	@ResponseBody
	public ResponseEntity<?> buscarClientePorId(@PathVariable Integer id) {
		Optional<ClienteEntity> cliente = repository.findById(id);
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}
}
