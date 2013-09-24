/**
 * 
 */
package com.bluesoft.gekson.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bluesoft.gekson.dao.FilmeDao;
import com.bluesoft.gekson.entidade.Filme;
import com.bluesoft.gekson.entidade.Usuario;
import com.bluesoft.gekson.servico.VotacaoServico;

/**
 * @author gekson
 *
 */
@Controller
@RequestMapping(value="/computarVoto")
@SessionAttributes({"filmes", "lista"})
public class ComputarVotoController {
	
	@Autowired
    private FilmeDao filmeDao;
	
	@Autowired
	private VotacaoServico votacaoServico;

	@RequestMapping(method=RequestMethod.GET)
    public String get(ModelMap model) {
		model.addAttribute("lista", model.get("lista"));
        model.addAttribute("usuario", new Usuario());
    	return "computarVoto";
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method=RequestMethod.POST, value="computarVoto")
    public String computarVoto(@Valid @ModelAttribute("usuario")Usuario usuario, BindingResult result,  ModelMap model) {    	
    	
    	if (result.hasErrors()) {
    		model.addAttribute("lista", model.get("lista"));
            return "computarVoto";
        }
    	
    	try {
    		votacaoServico.computarVotos(usuario,(List<Filme>) model.get("lista"));
    		List<Filme> ranking = filmeDao.listarFilmesOrdenadoPorVoto();
    		model.addAttribute("ranking", ranking);
    		return "ranking";
		} catch (UnexpectedRollbackException e) {
			model.addAttribute("erro", "E-mail já cadastrado.");
			return "computarVoto";
		}    	
    	
    }
}
