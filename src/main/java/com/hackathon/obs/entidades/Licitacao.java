package com.hackathon.obs.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Licitacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipo;
	private String nr_licitacao;
	private String nr_processo_licitatorio;
	private String data_publicacao;
	private String data_licitacao;
	@Column(length = 10000)
	private String objeto;
	private String valor;
	@ManyToOne
	private Usuario usuario; // usuario logado no momento;
	@ManyToOne
	private Unidade unidade; // apenas unidades true;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Historico> listaHistorico;

	public Licitacao(Long id, String tipo, String nr_licitacao, String nr_processo_licitatorio, String data_publicacao,
			String data_licitacao, String objeto, Usuario usuario_id, Unidade unidade_id,
			List<Historico> listaHistorico, String valor) {
		this.id = id;
		this.tipo = tipo;
		this.nr_licitacao = nr_licitacao;
		this.nr_processo_licitatorio = nr_processo_licitatorio;
		this.data_publicacao = data_publicacao;
		this.data_licitacao = data_licitacao;
		this.objeto = objeto;
		this.usuario = usuario_id;
		this.unidade = unidade_id;
		this.listaHistorico = new ArrayList<Historico>();
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNr_licitacao() {
		return nr_licitacao;
	}

	public void setNr_licitacao(String nr_licitacao) {
		this.nr_licitacao = nr_licitacao;
	}

	public String getNr_processo_licitatorio() {
		return nr_processo_licitatorio;
	}

	public void setNr_processo_licitatorio(String nr_processo_licitatorio) {
		this.nr_processo_licitatorio = nr_processo_licitatorio;
	}

	public String getData_publicacao() {
		return data_publicacao;
	}

	public void setData_publicacao(String data_publicacao) {
		this.data_publicacao = data_publicacao;
	}

	public String getData_licitacao() {
		return data_licitacao;
	}

	public void setData_licitacao(String data_licitacao) {
		this.data_licitacao = data_licitacao;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public List<Historico> getListaHistorico() {
		return listaHistorico;
	}

	public void setListaHistorico(List<Historico> listaHistorico) {
		this.listaHistorico = listaHistorico;
	}

	public Licitacao() {

	}
}
