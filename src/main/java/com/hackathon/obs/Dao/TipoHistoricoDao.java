package com.hackathon.obs.Dao;

import org.springframework.stereotype.Repository;

import com.hackathon.obs.entidades.TipoHistorico;

@Repository
public class TipoHistoricoDao extends GenericDao<TipoHistorico, Long>{

	public TipoHistoricoDao() {
		super(TipoHistorico.class);
		// TODO Auto-generated constructor stub
	}

}
