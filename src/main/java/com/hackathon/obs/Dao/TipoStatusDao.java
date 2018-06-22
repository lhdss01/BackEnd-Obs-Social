package com.hackathon.obs.Dao;

import org.springframework.stereotype.Repository;

import com.hackathon.obs.entidades.TipoStatus;
@Repository
public class TipoStatusDao extends GenericDao<TipoStatus, Long> {

	public TipoStatusDao() {
		super(TipoStatus.class);
	}

}
