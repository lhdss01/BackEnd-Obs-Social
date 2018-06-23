package com.hackathon.obs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hackathon.obs.entidades.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

}
