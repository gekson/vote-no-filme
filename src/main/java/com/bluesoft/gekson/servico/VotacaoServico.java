/**
 * 
 */
package com.bluesoft.gekson.servico;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bluesoft.gekson.dao.FilmeDao;
import com.bluesoft.gekson.dao.UsuarioDao;
import com.bluesoft.gekson.entidade.Filme;
import com.bluesoft.gekson.entidade.Usuario;

/**
 * @author gekson
 *
 */
@Component
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class VotacaoServico {

	@Autowired
    private FilmeDao filmeDao;
	
	@Autowired
    private UsuarioDao usuarioDao;
	
	public List<Filme> realizarVoto(List<Filme> filmes,
			Filme filmeVotado, List<Filme> votados) {
		
		filmes.remove(filmeVotado);
		
		//Votar no filme 
		Integer voto = filmeVotado.getVoto();
		filmeVotado.setVoto(++voto);		
		
		votados.add(filmeVotado);
		return votados;
	}

	public String computarVotos(Usuario usuario, List<Filme> votos) {
		String msg = "Sucesso";
		try {
			for (Filme filme : votos) {
				filmeDao.atualizarFilme(filme);
			}
			
			usuarioDao.gravarUsuario(usuario);
		} catch (ConstraintViolationException e) {
			msg = e.getMessage();
		} 
		
		return msg;
	}
	
}
