package com.bluesoft.gekson.controller;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class VotoVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Escolha um filme.")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	};

}
