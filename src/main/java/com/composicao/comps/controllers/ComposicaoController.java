package com.composicao.comps.controllers;

import java.util.List;

import javax.servlet.ServletException;
import javax.swing.ListModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.composicao.comps.modelos.Composicao;
import com.composicao.comps.modelos.Usuario;
import com.composicao.comps.reps.ComposicaoRepositorio;
import com.composicao.comps.reps.UsuarioRepositorio;

@Controller
@RequestMapping("/api")
public class ComposicaoController {
	
	@Autowired
	ComposicaoRepositorio compRep;
	
	@Autowired
	UsuarioRepositorio usuarioRep;
	
	
	
	@PostMapping("/{id}/addComp")
	public ResponseEntity<Composicao> adicionarComposicao(@RequestBody Composicao composicao, @PathVariable Long id) throws ServletException{
		
		Usuario usuarioLogado = usuarioRep.getOne(id);
		if(usuarioLogado == null) {
			throw new ServletException("O id de usuario passado nao existe!");
		} else {
			composicao.setUsuario(usuarioLogado);
			Composicao compCadastrada = compRep.save(composicao);
			
			return new ResponseEntity<Composicao>(compCadastrada, HttpStatus.CREATED);
		}	
	}
	
	@DeleteMapping("/removerComp/{titulo}")
	public ResponseEntity<Composicao> removerComposicao(@PathVariable String titulo){
		Composicao composicaoDeletada = compRep.procurarPeloTitulo(titulo);
		if(composicaoDeletada != null ) {
			compRep.delete(composicaoDeletada);
			return new ResponseEntity<Composicao>(composicaoDeletada, HttpStatus.OK);
			
		}
		
		return new ResponseEntity<Composicao>(HttpStatus.NOT_FOUND);
	
	}
	
	@GetMapping("/{id}/listarComps")
	public ResponseEntity<List<Composicao>> listarComposicoesUsuario(@PathVariable Long id) throws ServletException{
		Usuario usuario = usuarioRep.getOne(id);
		if(usuario != null) {
			List<Composicao> composicoes = usuario.getComposicoes();
			return new ResponseEntity<List<Composicao>>(composicoes, HttpStatus.OK);
		} else {
			throw new ServletException("Usuario nao encontrado!");
		}
	}
	
	@GetMapping("/listarComps")
	public ResponseEntity<List<Composicao>> listarComposicoes() {
		List<Composicao> comps = compRep.findAll();
		return new ResponseEntity<List<Composicao>>(comps, HttpStatus.OK);
	}
	

}
