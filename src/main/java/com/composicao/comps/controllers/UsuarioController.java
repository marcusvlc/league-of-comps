package com.composicao.comps.controllers;

import java.util.List;

import javax.servlet.ServletException;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.composicao.comps.modelos.Usuario;
import com.composicao.comps.reps.UsuarioRepositorio;

@RestController
//@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	UsuarioRepositorio usuarioRep;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioProcurado = usuarioRep.procurarPorEmail(usuario.getEmail());
		Usuario usuarioCadastrado;
		if (usuarioProcurado == null) {
			usuarioCadastrado = usuarioRep.save(usuario);
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.CONFLICT);
		}
		
		
		return new ResponseEntity<Usuario>(usuarioCadastrado, HttpStatus.CREATED);
	}
	
	@GetMapping("/api/usuarios")
	public ResponseEntity<List<Usuario>> listarUsuarios(){
		List<Usuario> usuarios = usuarioRep.findAll();
		
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping("/usuario/{email}")
	public ResponseEntity<Usuario> retornarUsuario(@PathVariable String email) throws ServletException{
		Usuario usuarioProcurado = usuarioRep.procurarPorEmail(email);
		if(usuarioProcurado != null) {
			return new ResponseEntity<Usuario>(usuarioProcurado, HttpStatus.OK);
		} else {
			throw new ServletException("Não existe usuário com o id passado");
		}
	}
}
