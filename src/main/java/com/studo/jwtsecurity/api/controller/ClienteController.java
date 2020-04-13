package com.studo.jwtsecurity.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.studo.jwtsecurity.domain.entity.ClienteEntity;
import com.studo.jwtsecurity.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/api/services/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@GetMapping("{id}")
	public ClienteEntity buscarClientePorId(@PathVariable Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontado"));

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteEntity salvar(@RequestBody ClienteEntity cliente) {

		return repository.save(cliente);

	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		Optional<ClienteEntity> cliente = repository.findById(id);
		if (cliente.isPresent()) {
			repository.delete(cliente.get());
			return;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontado");
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody ClienteEntity cliente) {

		repository.findById(id).map(clienteExistente -> {
			cliente.setId(clienteExistente.getId());
			repository.save(cliente);
			return clienteExistente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontado"));

	}
	

	@GetMapping
	public List<ClienteEntity> pesquisar(ClienteEntity filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);

		Example<ClienteEntity> example = Example.of(filtro, matcher);
		return repository.findAll(example);

	}
}
