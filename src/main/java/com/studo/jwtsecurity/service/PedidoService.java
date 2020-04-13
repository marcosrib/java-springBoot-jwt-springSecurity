package com.studo.jwtsecurity.service;

import com.studo.jwtsecurity.api.dto.PedidoDTO;
import com.studo.jwtsecurity.domain.entity.PedidoEntity;

public interface PedidoService {
	
	PedidoEntity salvar(PedidoDTO dto);
}
