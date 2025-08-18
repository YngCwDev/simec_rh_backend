package com.mec.simec_rh.Repositories;

import com.mec.simec_rh.Entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepositoryInterface extends JpaRepository<Funcionario, Long> {

}
