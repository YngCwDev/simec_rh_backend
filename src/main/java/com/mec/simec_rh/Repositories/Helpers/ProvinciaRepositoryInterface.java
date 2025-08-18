package com.mec.simec_rh.Repositories.Helpers;

import com.mec.simec_rh.Entities.Alocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepositoryInterface extends JpaRepository<Alocacao, Long> {
}
