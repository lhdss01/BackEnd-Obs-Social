package com.hackathon.obs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hackathon.obs.entidades.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

}
