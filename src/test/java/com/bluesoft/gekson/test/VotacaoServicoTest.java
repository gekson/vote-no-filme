package com.bluesoft.gekson.test;

import java.util.ArrayList;
import java.util.Collections;
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
import com.bluesoft.gekson.dao.UsuarioDao;
import com.bluesoft.gekson.entidade.Filme;
import com.bluesoft.gekson.entidade.Usuario;
import com.bluesoft.gekson.servico.VotacaoServico;

/**
 * Classe de teste da classe VotacaoServicoTest
 * @author gekson
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-context.xml",
"classpath:/META-INF/spring/applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class VotacaoServicoTest {

	@Autowired
	private VotacaoServico votacaoServico;
	
	@Autowired
    private FilmeDao filmeDao;
	
	@Autowired
    private UsuarioDao usuarioDao;
	
//	@Test
	public void testRealizarVoto() {
		//Busca os filmes
		List<Filme> filmes = filmeDao.listarFilmes();
		Collections.shuffle(filmes); //Embaralha a lista, para não apresentar sempre a mesma ordem para usuários diferentes. 
		Filme filme2 = filmes.get(1);								
		
		//lista de filmes que recebeu voto do usuario
		List<Filme> filmesVotado = new ArrayList<Filme>();
		
		//chamar metodo da classe de negocio, que realiza a votação
		filmesVotado = votacaoServico.realizarVoto(filmes, filme2, filmesVotado);
		
		Assert.assertTrue(filmes.size() == 4);
		Assert.assertTrue(filmesVotado.size() == 1);
		
	}
	
//	@Test
	public List<Filme> testRealizarTodosVotos() {
		//Busca os filmes
		List<Filme> filmes = filmeDao.listarFilmes();								
		
		//lista de filmes que recebeu voto do usuario
		List<Filme> filmesVotado = new ArrayList<Filme>();				
		
		for (int i = 0; i < 4; i++) {									
			Collections.shuffle(filmes);  
			Filme filme2 = filmes.get(1);
			
			filmesVotado = votacaoServico.realizarVoto(filmes, filme2, filmesVotado);
		}
		
		Assert.assertTrue(filmes.size() == 1);
		Assert.assertTrue(filmesVotado.size() == 4);
		
		return filmesVotado;
	}
	
	@Test
	public void testVotoNUsuarios() {
		for (int i = 0; i < 2; i++) {
			List<Filme> votos = testRealizarTodosVotos();

			Usuario usuario = new Usuario();
			usuario.setNome("Usuario " + i);
			usuario.setEmail("usu"+i+"@teste.com");
			
			String msg = votacaoServico.computarVotos(usuario, votos);
			
			Assert.assertEquals("Sucesso", msg);
		}				
		
		List<Usuario> usuarios = usuarioDao.listarUsuarios();
		Assert.assertEquals(2, usuarios.size());
		
		List<Filme> filmes = filmeDao.listarFilmesOrdenadoPorVoto();
		for (Filme filme : filmes) {
			System.out.println(filme.getNome() + " -  - Ano: " + filme.getAno() + " - Votos: " + filme.getVoto() );	
		}		
	}
}
