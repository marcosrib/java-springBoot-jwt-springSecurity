package com.studo.jwtsecurity.api.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {

	private Integer codigo;
	private String cpf;
	private String nomeCliete;
	private BigDecimal total;
	private List<InformacoesItemPedidoDTO> item;
}
