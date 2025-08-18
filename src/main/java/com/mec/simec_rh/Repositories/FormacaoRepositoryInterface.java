package com.mec.simec_rh.Repositories;

import com.mec.simec_rh.Entities.Formacao;
import com.mec.simec_rh.Entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormacaoRepositoryInterface extends JpaRepository<Formacao, Long> {
}
