package com.estoqueapi.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoqueapi.model.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	Optional<Produto> findByNomeAndUsuario(String nome, Long usuario);
	Optional<Produto> findById(Long id);
	Collection<? extends Produto> findAllByUsuario(Long id_usuario);
}
  