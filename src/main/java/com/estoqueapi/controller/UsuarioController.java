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
import com.estoqueapi.model.Usuario;
import com.estoqueapi.repository.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarios;
	
	@GetMapping
	public List<Usuario> listar() {
		return usuarios.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findIdUsuario(@PathVariable Long id) {
		Optional<Usuario> usuarioOptional = usuarios.findById(id);
		
		if (usuarioOptional.toString().equals("Optional.empty")) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuarioOptional.get());
	}
	
	@GetMapping("/{usuario}/{senha}")
	public ResponseEntity<Usuario> loginUsuario(@PathVariable String usuario, @PathVariable String senha) {
		Optional<Usuario> usuarioOptional = usuarios.findByUsuarioAndSenha(usuario, senha);
		
		if (usuarioOptional.toString().equals("Optional.empty")) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuarioOptional.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario adicionaUsuario(@Valid @RequestBody Usuario usuario) {
		Optional<Usuario> UsuarioExistente = usuarios.findByUsuario(usuario.getUsuario());
		if (UsuarioExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Usuario não disponivel");
		}

		return usuarios.save(usuario);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Usuario alteraUsuario(@Valid @RequestBody Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarios.findById(usuario.getId());

		if (usuarioExistente.isPresent()) {
			return usuarios.save(usuario);
		}
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario não encontrado");
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.GONE)
	public ResponseEntity<Usuario> apagaUsuario(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarios.findById(id);
		
		if (usuario.toString().equals("Optional.empty")) {
			return ResponseEntity.notFound().build();
		}
		
		usuarios.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
}
