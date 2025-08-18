package com.mec.simec_rh.Repositories;

import com.mec.simec_rh.Entities.Funcionario;
import com.mec.simec_rh.Entities.HistoricoCarreira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HistoricoCarreiraRepositoryInterface extends JpaRepository<HistoricoCarreira, Long> {
}
