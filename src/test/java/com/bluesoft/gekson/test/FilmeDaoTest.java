/**
 * 
 */
package com.bluesoft.gekson.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bluesoft.gekson.dao.FilmeDao;
import com.bluesoft.gekson.entidade.Filme;

/**
 * Classe de teste da interface FilmeDao
 * @author gekson
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-context.xml",
"classpath:/META-INF/spring/applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class FilmeDaoTest {

	@Autowired
    private FilmeDao filmeDao;
	
	@Test
	public void testBuscarFilme() {
		 Filme filme = filmeDao.buscarPorId(1L);	
		 
		 Assert.assertEquals("Harry Potter", filme.getNome());
	     Assert.assertEquals("2005", filme.getAno());	       
	}
	
	@Test
	public void testListarFilmes() {
		List<Filme> filmes = filmeDao.listarFilmes();
		Assert.assertTrue(filmes.size() > 0);				
	}
	
	@Test
	public void testAtualizarVoto() {
		Filme filme = filmeDao.buscarPorId(1L);	

		Integer voto = filme.getVoto();
		filme.setVoto(++voto);
		filmeDao.atualizarFilme(filme);
		Filme filmeModificado = filmeDao.buscarPorId(1L);	
		Assert.assertEquals("1", filmeModificado.getVoto().toString());

	}
	
//	@Test
	public void testGravarFilme() {
		Filme f = new Filme();
		f.setAno("2013");
		f.setNome("teste");
		f.setVoto(0);
		filmeDao.salvarFilme(f);
		
		Long id = f.getId();
        Assert.assertNotNull(id);                
	}
	
	@Test
	public void testBuscarFilmesOrdenadoPorVoto() {		
		Filme filme = filmeDao.buscarPorId(1L);	
		filme.setVoto(2);
		filmeDao.atualizarFilme(filme);
		
		Filme filme2 = filmeDao.buscarPorId(2L);	
		filme2.setVoto(4);
		filmeDao.atualizarFilme(filme2);
		
		List<Filme> filmes = filmeDao.listarFilmesOrdenadoPorVoto();
		Assert.assertTrue(filmes.size() > 0);	
		
		for (Filme f: filmes) {
			System.out.println(f.getNome() + " -  - Ano: " + f.getAno() + " - Votos: " + f.getVoto() );	
		}	
	}
}