package com.hackathon.obs.entidades;

import java.io.File;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Importacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Usuario usuario;
	private String descricao;
	private File licitacao;
	

	public File getLicitacao() {
		return licitacao;
	}

	public void setLicitacao(File licitacao) {
		this.licitacao = licitacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario_id() {
		return usuario;
	}

	public void setUsuario_id(Usuario usuario_id) {
		this.usuario = usuario_id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Importacao(Long id, Usuario usuario_id, String descricao) {
		this.id = id;
		this.usuario = usuario_id;
		this.descricao = descricao;
	}

	public Importacao() {
		// TODO Auto-generated constructor stub
	}

}
