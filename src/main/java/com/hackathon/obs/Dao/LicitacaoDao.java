package com.hackathon.obs.Dao;

import org.springframework.stereotype.Repository;

import com.hackathon.obs.entidades.Licitacao;

@Repository
public class LicitacaoDao extends GenericDao<Licitacao, Long>{

	public LicitacaoDao() {
		super(Licitacao.class);
	}
	
	
}
