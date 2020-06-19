package com.estoqueapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoqueapi.model.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByUsuarioAndSenha(String usuario, String senha);
	Optional<Usuario> findByUsuario(String usuario);
	Optional<Usuario> findById(Long id);
	
}
