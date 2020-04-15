package com.studo.jwtsecurity.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "pedido")
public class PedidoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private ClienteEntity cliente;
	@Column(name = "data_pedido")
	private LocalDate dataPedito;

	@Column(name = "total", scale= 2, precision = 20)
	private BigDecimal total;
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
	private List<ItemPedidoEntity> itens;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public LocalDate getDataPedito() {
		return dataPedito;
	}

	public void setDataPedito(LocalDate dataPedito) {
		this.dataPedito = dataPedito;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<ItemPedidoEntity> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoEntity> itens) {
		this.itens = itens;
	}

	
}
