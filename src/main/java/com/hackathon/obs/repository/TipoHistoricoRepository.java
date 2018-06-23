package com.hackathon.obs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hackathon.obs.entidades.TipoHistorico;

@Repository
public interface TipoHistoricoRepository extends JpaRepository<TipoHistorico, Long> {

}
