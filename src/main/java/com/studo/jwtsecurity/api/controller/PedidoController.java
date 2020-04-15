package com.studo.jwtsecurity.api.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.studo.jwtsecurity.api.dto.InformacoesItemPedidoDTO;
import com.studo.jwtsecurity.api.dto.InformacoesPedidoDTO;
import com.studo.jwtsecurity.api.dto.PedidoDTO;
import com.studo.jwtsecurity.domain.entity.ItemPedidoEntity;
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

	@GetMapping("{id}")
	public InformacoesPedidoDTO buscarPorId(@PathVariable Integer id) {
		return pedidoService.obterPedidoCompleto(id).map(p -> converter(p))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

	}

	private InformacoesPedidoDTO converter(PedidoEntity p) {
		
		return  InformacoesPedidoDTO
			.builder()
			.codigo(p.getId())
			.nomeCliete(p.getCliente().getNome())
			.total(p.getTotal())
			.item(converter(p.getItens()))
			.build();	
	}

	private List<InformacoesItemPedidoDTO> converter(List<ItemPedidoEntity> itens) {
	    if(CollectionUtils.isEmpty(itens)) {
	    	return Collections.emptyList();
	    }
		return itens.stream().map(
				item -> InformacoesItemPedidoDTO
					.builder()
					.precoUnitario(item.getProduto().getPreco())
					.quantidade(item.getQuatidade())
					.descricaoProduto(item.getProduto().getDescricao())
					.build()
					).collect(Collectors.toList());

	}
}
