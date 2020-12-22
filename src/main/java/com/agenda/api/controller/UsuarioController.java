package com.agenda.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.api.model.Usuario;
import com.agenda.api.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired //injecao de dependecias conceito - SOLID eh um padrao de design de codigo
	private UsuarioService usuarioService;
	
	
	@GetMapping
	public ResponseEntity<List<Usuario>> buscarTodosUsuarios(){
		return ResponseEntity.ok(usuarioService.listarUsuarios());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id){
		Optional<Usuario> user = usuarioService.buscarUsuario(id);
		if(user.isEmpty()) {
			return ResponseEntity.notFound().build();	
		}
		return ResponseEntity.ok(user.get());
		
	}
	
	@PostMapping
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario user){
		
		Usuario saved = usuarioService.save(user);
		
		return new ResponseEntity<Usuario>(saved, HttpStatus.CREATED);
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
		
		Usuario user = usuarioService.update(usuario,id);
		if(user == null) {
			return ResponseEntity.notFound().build();			
		}
		
		return ResponseEntity.ok(user);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
		usuarioService.delete(id);
		return ResponseEntity.noContent().build();
		
	}

}
