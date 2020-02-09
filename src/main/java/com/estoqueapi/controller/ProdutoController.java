package com.estoqueapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.estoqueapi.model.Produto;
import com.estoqueapi.model.Usuario;
import com.estoqueapi.repository.ProdutoRepository;

@CrossOrigin
@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto adicionaProduto(@Valid @RequestBody Produto produto) {
		Optional<Produto> produtoExistente = produtoRepository.findByNome(produto.getNome());

		if (produtoExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome do produto já usado");
		}

		return produtoRepository.save(produto);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Produto alteraProduto(@Valid @RequestBody Produto produto) {
		Optional<Produto> produtoExistent = produtoRepository.findById(produto.getId());

		if (produtoExistent.isPresent()) {
			return produtoRepository.save(produto);
		}

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado");
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.GONE)
	public ResponseEntity<Produto> apagarProduto(@PathVariable Long id) {
		Optional<Produto> produtoExistent = produtoRepository.findById(id);

		if (produtoExistent.toString().equals("Optional.empty")) {
			return ResponseEntity.notFound().build();
		}

		produtoRepository.deleteById(id);

		return ResponseEntity.ok().build();
	}
}
