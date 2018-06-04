package com.composicao.comps.controllers;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.composicao.comps.modelos.Usuario;
import com.composicao.comps.reps.UsuarioRepositorio;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {
	
	@Autowired
	UsuarioRepositorio usuarioRep;
	
	@PostMapping("/autenticar")
	public LoginResponse autenticar(@RequestBody Usuario usuario) throws Exception {
		if(usuario.getEmail().equals(null) || usuario.getSenha().equals(null)) {
			throw new ServletException("Email e senha são obrigatórios para autenticação");
		}
		
		String token = "";
		Usuario usuarioAutenticado = usuarioRep.procurarPorEmail(usuario.getEmail());
		if (usuarioAutenticado != null) {
			
			if(usuario.getSenha().equals(usuarioAutenticado.getSenha())) {
				// TOKEN GERADO PELO USUARIO AUTENTICADO COM DURACAO DE 15MIN.
				token = Jwts.
						builder().
						setSubject(usuarioAutenticado.getEmail())
						.signWith(SignatureAlgorithm.HS512, "uska")
						.setExpiration(new Date(System.currentTimeMillis() +  15 * 60 * 1000))
						.compact();
				
				System.out.println(token);
			} else {
				throw new ServletException("Dados incorretos");
			}
			
			
			
		} else {
			throw new ServletException("Usuario nao encontrado!");
		}

		return new LoginResponse(token);
	}
	
	private class LoginResponse {
		public String token;
		
		public LoginResponse(String token) {
			this.token = token;
		}
	}
}
