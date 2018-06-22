package com.hackathon.obs.Dao;

import org.springframework.stereotype.Repository;

import com.hackathon.obs.entidades.Importacao;

@Repository
public class ImportacaoDao extends GenericDao<Importacao, Long> {

	public ImportacaoDao() {
		super(Importacao.class);
	}

}
