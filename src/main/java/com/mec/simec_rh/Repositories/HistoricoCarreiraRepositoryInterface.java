package com.mec.simec_rh.Repositories;

import com.mec.simec_rh.Entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoCarreiraRepositoryInterface extends JpaRepository<Funcionario, Long> {
}
