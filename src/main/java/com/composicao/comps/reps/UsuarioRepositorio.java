package com.composicao.comps.reps;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.composicao.comps.modelos.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	
	@Query(value = "Select u from Usuario u where u.email=:pemail")
	public Usuario procurarPorEmail(@Param("pemail") String email);
	
}
