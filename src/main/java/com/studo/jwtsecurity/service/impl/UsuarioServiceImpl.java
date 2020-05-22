package com.studo.jwtsecurity.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.studo.jwtsecurity.domain.entity.Usuario;
import com.studo.jwtsecurity.domain.repository.UsuarioRepository;
@Service
public class UsuarioServiceImpl implements UserDetailsService {
    @Autowired
	private PasswordEncoder encoder;
	
    private UsuarioRepository repository;
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = repository.findByLogin(username).orElseThrow(() ->  new UsernameNotFoundException("Usuario não encontrado"));
	    String[] roles = usuario.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};
		return User
				.builder()
				.username(usuario.getLogin())
				.password(encoder.encode(usuario.getSenha()))
				.roles(roles)
				.build();
	}
	
    @Transactional
	public Usuario salvar(Usuario usuario) {
		return repository.save(usuario);
	}
}
