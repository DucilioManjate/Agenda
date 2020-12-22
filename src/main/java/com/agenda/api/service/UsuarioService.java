package com.agenda.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.api.model.Usuario;
import com.agenda.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	   
	public List<Usuario> listarUsuarios(){
		return usuarioRepository.findAll() ;
	}
	
	public Optional <Usuario> buscarUsuario(Long id) {
		return usuarioRepository.findById(id);
		
	}
	public Usuario save(Usuario usuario) {
		// implementar a verificacao de existencia de email ja cadastrado no sistema
		return usuarioRepository.save(usuario);
	}
	public Usuario update (Usuario usuario, Long id) {
		Optional<Usuario> user = usuarioRepository.findById(id);
		if(user.isEmpty()) {
			return null;			
		}
		
		Usuario saved = user.get();
		
		saved.setNome(usuario.getNome());
		saved.setEmail(usuario.getEmail());
		saved.setSenha(usuario.getSenha());
		return usuarioRepository.save(saved);
		
		
	}
	public void delete(Long id) {
		
		Optional<Usuario> user = usuarioRepository.findById(id);
		if(user.isEmpty()) {
			return;	
			
		}
		usuarioRepository.deleteById(id);
	}

}
	
	