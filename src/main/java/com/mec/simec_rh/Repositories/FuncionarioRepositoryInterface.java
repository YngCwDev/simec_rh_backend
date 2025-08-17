package com.mec.simec_rh.Repositories;

import com.mec.simec_rh.Entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepositoryInterface extends JpaRepository<Funcionario, Long> {

}
