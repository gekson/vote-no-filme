package com.bluesoft.gekson.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bluesoft.gekson.dao.FilmeDao;
import com.bluesoft.gekson.entidade.Filme;

@Repository
@Transactional
public class FilmeDaoImpl implements FilmeDao {

	@Autowired
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Filme> listarFilmes() {
		List<Filme> filmes = new ArrayList<Filme>();
		filmes = em.createNamedQuery("buscarFilmes").getResultList();
		return filmes;
	}

	@Override
	public void atualizarFilme(Filme filme) {
		em.merge(filme);
		
	}

	@Override
	public Filme buscarPorId(Long id) {
		return em.find(Filme.class, id);
	}

	@Override
	public void salvarFilme(Filme f) {
		em.persist(f);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Filme> listarFilmesOrdenadoPorVoto() {
		List<Filme> filmes = new ArrayList<Filme>();
		filmes = em.createNamedQuery("buscarFilmesOrdenadoPorVoto").getResultList();
		return filmes;
	}
	
	@Override
	public void popularBanco() {
		String sql = "insert into Filme (id, nome, voto, ano) values (6,'Harry Potter',0,'2005');"
				+ "insert into Filme (id, nome, voto, ano) values (7,'Batman',0,'2010');"
				+ "insert into Filme (id, nome, voto, ano) values (8,'Senhor dos Anéis',0,'2008');"
				+ "insert into Filme (id, nome, voto, ano) values (9,'Wolverine',0,'2010');"
				+ "insert into Filme (id, nome, voto, ano) values (10,'Armagedon',0,'2000')";
		
		Query q = em.createNativeQuery(sql);
		q.executeUpdate();
	
	}

}
