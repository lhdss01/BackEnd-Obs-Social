package com.hackathon.obs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hackathon.obs.entidades.Licitacao;

@Repository
public interface LicitacaoRepository extends JpaRepository<Licitacao, Long> {

}
