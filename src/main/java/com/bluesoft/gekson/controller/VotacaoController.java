/**
 * 
 */
package com.bluesoft.gekson.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value="/")
@SessionAttributes({"filmes", "lista"})
public class VotacaoController {

//	private static boolean populado = false;
	
	@Autowired
    private FilmeDao filmeDao;
	
	@Autowired
	private VotacaoServico votacaoServico;
	
	private List<Filme> filmes = new ArrayList<Filme>();
	private List<Filme> filmesVotados = new ArrayList<Filme>();	

//	@PostConstruct
//    public void popularDatabase() {
//		if (populado) {
//            return;
//        }
//				
//		filmeDao.popularBanco();
//		
//		populado = true;
//	}
	
    @RequestMapping(method=RequestMethod.GET)
    public String listarFilmes(ModelMap model) {
    	filmes = filmeDao.listarFilmes();
    	Collections.shuffle(filmes); 
    	
    	model.addAttribute("filmes", filmes);    
    	model.addAttribute("voto", new VotoVo());
    	model.addAttribute("filme1", filmes.get(0));    	
    	model.addAttribute("filme2", filmes.get(1));
    	filmesVotados.clear();
    	return "index";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String votar(@Valid @ModelAttribute("voto")VotoVo voto, BindingResult result,  ModelMap model)
    {
        if (result.hasErrors()) {
        	model.addAttribute("filme1", filmes.get(0));    	
        	model.addAttribute("filme2", filmes.get(1));
            return "index";
        }
         	
    	Filme filme = filmeDao.buscarPorId(voto.getId());    	
    	
        filmesVotados = votacaoServico.realizarVoto(filmes, filme, filmesVotados);                
        
        if(filmes.size()>1) {
        	Collections.shuffle(filmes);
        	model.addAttribute("filme1", filmes.get(0));    	
        	model.addAttribute("filme2", filmes.get(1));
            model.addAttribute("teste", filme);
            
        	return "index";
        }
        model.addAttribute("lista", filmesVotados);
        model.addAttribute("usuario", new Usuario());        
        return "computarVoto";
        
    }            
}

