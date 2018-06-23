package com.hackathon.obs.importacao;

import javax.validation.constraints.NotNull;

public class ImportacaoRequest {

	private String descricao;
	@NotNull
	private Long usuarioId;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

}
