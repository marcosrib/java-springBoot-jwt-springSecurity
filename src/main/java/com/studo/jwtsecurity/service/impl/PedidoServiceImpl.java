package com.studo.jwtsecurity.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.studo.jwtsecurity.api.dto.ItemPedidoDTO;
import com.studo.jwtsecurity.api.dto.PedidoDTO;
import com.studo.jwtsecurity.domain.entity.ClienteEntity;
import com.studo.jwtsecurity.domain.entity.ItemPedidoEntity;
import com.studo.jwtsecurity.domain.entity.PedidoEntity;
import com.studo.jwtsecurity.domain.entity.ProdutoEntity;
import com.studo.jwtsecurity.domain.repository.ClienteRepository;
import com.studo.jwtsecurity.domain.repository.ItemPedidoRepository;
import com.studo.jwtsecurity.domain.repository.PedidoRepository;
import com.studo.jwtsecurity.domain.repository.ProdutoRepository;
import com.studo.jwtsecurity.exception.RegraNegocioException;
import com.studo.jwtsecurity.service.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ClienteRepository clienteRepository;
	private final ProdutoRepository produtoRepository;
	private final ItemPedidoRepository ItemPedidoRepository;

	@Override
	@Transactional
	public PedidoEntity salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		ClienteEntity cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new RegraNegocioException("Código de cliente invalido."));
		PedidoEntity pedido = new PedidoEntity();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedito(LocalDate.now());
		pedido.setCliente(cliente);
		List<ItemPedidoEntity> itemsPedido = converterItems(pedido, dto.getItems());
		pedidoRepository.save(pedido);
		ItemPedidoRepository.saveAll(itemsPedido);
		return pedido;
	}

	private List<ItemPedidoEntity> converterItems(PedidoEntity pedido, List<ItemPedidoDTO> items) {
		if (items.isEmpty()) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
		}

		return items.stream().map(dto -> {
			Integer idProduto = dto.getProduto();
			ProdutoEntity produto = produtoRepository.findById(idProduto)
					.orElseThrow(() -> new RegraNegocioException("Código de produto inválido"));

			ItemPedidoEntity itemPedido = new ItemPedidoEntity();
			itemPedido.setQuatidade(dto.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			return itemPedido;
		}).collect(Collectors.toList());

	}

}
