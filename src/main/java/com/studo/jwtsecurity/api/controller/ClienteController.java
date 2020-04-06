package com.studo.jwtsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
public class ClienteController {
@GetMapping("cliente")
 public String getCliente() {
	 return "clieent";
 }
}
