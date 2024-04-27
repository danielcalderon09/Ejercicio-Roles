package com.ejercicio.pw.pwroles.repositorio;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ejercicio.pw.pwroles.usuario.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>{

	void save(Usuario empleado);

	List<Usuario> findAll();

	void deleteById(Long id);

	Object findById(Long id);

}
