package com.composicao.comps.controllers;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class FiltroControllers extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest requisicao = (HttpServletRequest) request;
		
		String cabecalho = requisicao.getHeader("Authorization");
		
		if(cabecalho == null || !cabecalho.startsWith("Bearer ")) {
			throw new ServletException("Token inexistente ou inválido!");
		}
		
		String token = cabecalho.substring(7);
		
		try {
			Jwts.parser().setSigningKey("uska").parseClaimsJws(token).getBody();
		}catch(SignatureException e) {
			throw new ServletException("Token inválido");
		}
		
		
		chain.doFilter(request, response);
	}

}
