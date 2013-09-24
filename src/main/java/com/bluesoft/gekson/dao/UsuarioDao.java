/**
 * 
 */
package com.bluesoft.gekson.dao;

import java.util.List;

import com.bluesoft.gekson.entidade.Usuario;

/**
 * @author gekson
 *
 */
public interface UsuarioDao {

	void gravarUsuario(Usuario usuario);
	List<Usuario> listarUsuarios();

}
