package com.hackathon.obs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hackathon.obs.entidades.TipoStatus;

@Repository
public interface TipoStatusRepository extends JpaRepository<TipoStatus, Long>{

}
