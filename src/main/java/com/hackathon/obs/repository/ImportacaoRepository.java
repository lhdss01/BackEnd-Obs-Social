package com.hackathon.obs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hackathon.obs.entidades.Importacao;

@Repository
public interface ImportacaoRepository extends JpaRepository<Importacao, Long> {

}
