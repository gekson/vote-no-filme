/**
 * 
 */
package com.bluesoft.gekson.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author gekson
 * Classe de entidade, responsável por implementar a tabela Usuário.
 * Um e-mail, só pode ser cadastrado uma única vez.
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@NamedQueries({
	   @NamedQuery(name="buscarUsuarios",query="select u from Usuario u")	   
	})
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message="Campo Obrigatório")
	@Size(min = 1, max = 60, message="O campo deve ter entre 1 e 60 caracteres.")
	private String nome;

	@NotNull(message="Campo Obrigatório")
	@NotEmpty(message="Campo Obrigatório")
	@Email
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
