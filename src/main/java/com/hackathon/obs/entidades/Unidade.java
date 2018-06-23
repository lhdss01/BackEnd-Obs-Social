package com.hackathon.obs.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Unidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private boolean status = true;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return descricao;
	}

	public void setNome(String nome) {
		this.descricao = nome;
	}

	public Unidade(Long id, String nome, boolean status) {
		this.id = id;
		this.descricao = nome;
		this.status = status;
	}

	public Unidade() {
	}

}
