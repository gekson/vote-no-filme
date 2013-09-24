/**
 * 
 */
package com.bluesoft.gekson.dao;

import java.util.List;

import com.bluesoft.gekson.entidade.Filme;

/**
 * @author gekson
 *
 */
public interface FilmeDao {

	List<Filme> listarFilmes();

	void atualizarFilme(Filme filme);

	Filme buscarPorId(Long id);

	void salvarFilme(Filme f);

	List<Filme> listarFilmesOrdenadoPorVoto();
	
	void popularBanco();

}
