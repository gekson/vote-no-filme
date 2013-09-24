package com.bluesoft.gekson.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bluesoft.gekson.dao.UsuarioDao;
import com.bluesoft.gekson.entidade.Usuario;

@Repository
@Transactional
public class UsuarioDaoImpl implements UsuarioDao {

	@Autowired
    private EntityManager em;
	
	@Override
	public void gravarUsuario(Usuario usuario) {
		try {
			em.persist(usuario);
		} catch (ConstraintViolationException e) {
			throw new ConstraintViolationException("E-mail já cadastrado.", null); 
		}
				
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = em.createNamedQuery("buscarUsuarios").getResultList();
		return usuarios;
	}

}
