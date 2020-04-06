package com.studo.jwtsecurity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studo.jwtsecurity.domain.entity.ProdutoEntity;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

}
