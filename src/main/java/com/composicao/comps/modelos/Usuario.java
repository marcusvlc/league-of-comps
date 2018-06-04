package com.composicao.comps.modelos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
public class Usuario implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String email;
	private String senha;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="usuario")
	private List<Composicao> composicoes;
	
	public Usuario() {
		
	}
	
	
	

	public List<Composicao> getComposicoes() {
		return composicoes;
	}



	public void setComposicoes(List<Composicao> composicoes) {
		this.composicoes = composicoes;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
