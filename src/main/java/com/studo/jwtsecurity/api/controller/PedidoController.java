package com.studo.jwtsecurity.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.studo.jwtsecurity.api.dto.PedidoDTO;
import com.studo.jwtsecurity.domain.entity.PedidoEntity;
import com.studo.jwtsecurity.service.PedidoService;

@RestController
@RequestMapping("/api/services/pedidos")
public class PedidoController {

	private PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer salvar(@RequestBody PedidoDTO dto) {
		PedidoEntity pedito = pedidoService.salvar(dto);
		return pedito.getId();
		
	}

}
