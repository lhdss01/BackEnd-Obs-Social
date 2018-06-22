package com.hackathon.obs.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hackathon.obs.entidades.Historico;

@Repository
public class HistoricoDao extends GenericDao<Historico, Long> {

	public HistoricoDao() {
		super(Historico.class);
	}

	public List<Historico> listar() {
		return this.listar();
	}
}
