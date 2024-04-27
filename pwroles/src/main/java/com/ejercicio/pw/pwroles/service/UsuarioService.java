package com.ejercicio.pw.pwroles.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ejercicio.pw.pwroles.usuario.Usuario;


public interface UsuarioService {
	public List<Usuario> findAll();

	public Page<Usuario> findAll(Pageable pageable);

	public void save(Usuario usuario);

	public Usuario findOne(Long id);

	public void delete(Long id);
}
