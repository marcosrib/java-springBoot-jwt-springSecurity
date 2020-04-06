package com.studo.jwtsecurity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studo.jwtsecurity.domain.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

}
