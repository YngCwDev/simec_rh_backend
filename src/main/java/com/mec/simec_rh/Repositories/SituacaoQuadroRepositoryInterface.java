package com.mec.simec_rh.Repositories;


import com.mec.simec_rh.Entities.SituacaoQuadro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituacaoQuadroRepositoryInterface extends JpaRepository<SituacaoQuadro, Long> {
}
