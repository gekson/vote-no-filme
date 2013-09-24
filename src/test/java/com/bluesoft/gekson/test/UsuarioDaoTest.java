/**
 * 
 */
package com.bluesoft.gekson.test;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bluesoft.gekson.dao.UsuarioDao;
import com.bluesoft.gekson.entidade.Usuario;

/**
 * Classe de teste da interface UsuarioDao
 * @author gekson
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-context.xml",
"classpath:/META-INF/spring/applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class UsuarioDaoTest {

	@Autowired
    private UsuarioDao usuarioDao;
	
	@Test
	public void testGravarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("Usu 1");
		usuario.setEmail("usu1@teste.com");
		
		usuarioDao.gravarUsuario(usuario);
		Long id = usuario.getId();
        Assert.assertNotNull(id);        
	}
	
	@Test
	public void testLancarExcessaoEmailRepetido() {
		try {
			Usuario usuario = new Usuario();
			usuario.setNome("Usu 1");
			usuario.setEmail("usu1@teste.com");
			
			usuarioDao.gravarUsuario(usuario);
			Long id = usuario.getId();
	        Assert.assertNotNull(id);
	        
	        Usuario usuario2 = new Usuario();
			usuario.setNome("Usu 2");
			usuario.setEmail("usu1@teste.com");
			usuarioDao.gravarUsuario(usuario2);
			Long id2 = usuario2.getId();
	        Assert.assertNotNull(id2);
		} catch (Exception e) {
			Assert.assertEquals("E-mail já cadastrado.", e.getMessage());
		}		
		
	}
}
