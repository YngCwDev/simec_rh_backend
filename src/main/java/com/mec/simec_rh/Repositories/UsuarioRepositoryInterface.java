package com.mec.simec_rh.Repositories;

import com.mec.simec_rh.Entities.Funcionario;
import com.mec.simec_rh.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepositoryInterface extends JpaRepository<Usuario, UUID> {
}
