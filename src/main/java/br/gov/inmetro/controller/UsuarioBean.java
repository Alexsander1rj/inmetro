package br.gov.inmetro.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import br.gov.inmetro.model.Usuario;
import br.gov.inmetro.repository.UsuarioRepository;

@Named
public class UsuarioBean {
	private String nomeUsuario;
	
	@Inject
	private UsuarioRepository usuarios;

	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public String getSaudacao(){
		String saudacao = "";
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Usuario usuario = usuarios.usuarioPorId(user.getUsername());
		
		saudacao = "Bem-vindo, " + usuario.getNome();
		
		return saudacao;
	}
}
