package com.composicao.comps.reps;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.composicao.comps.modelos.Composicao;
import com.composicao.comps.modelos.Usuario;

@Repository
public interface ComposicaoRepositorio extends JpaRepository<Composicao, Long> {
	
	@Query(value = "Select u from Composicao u where u.titulo=:ptitulo")
	public Composicao procurarPeloTitulo(@Param("ptitulo") String titulo);
}
