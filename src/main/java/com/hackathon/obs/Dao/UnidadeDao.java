package com.hackathon.obs.Dao;

import org.springframework.stereotype.Repository;

import com.hackathon.obs.entidades.Unidade;

@Repository
public class UnidadeDao extends GenericDao<Unidade, Long>{

	public UnidadeDao( ) {
		super(Unidade.class);
	}

	
}
