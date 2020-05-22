package com.studo.jwtsecurity.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studo.jwtsecurity.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  
	Optional<Usuario> findByLogin(String login);
}
