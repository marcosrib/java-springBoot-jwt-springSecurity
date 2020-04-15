package com.studo.jwtsecurity.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.studo.jwtsecurity.domain.entity.PedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer>{
	@Query("select p from PedidoEntity  p where p.id = :id")
	Optional<PedidoEntity> findByIdFetchItens(@Param("id") Integer id);
}
