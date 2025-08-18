package com.mec.simec_rh.Services;

import com.mec.simec_rh.Entities.Alocacao;
import com.mec.simec_rh.Entities.Formacao;
import com.mec.simec_rh.Entities.Funcionario;
import com.mec.simec_rh.Entities.HistoricoCarreira;
import com.mec.simec_rh.Repositories.AlocacaoRepositoryInterface;
import com.mec.simec_rh.Repositories.FormacaoRepositoryInterface;
import com.mec.simec_rh.Repositories.FuncionarioRepositoryInterface;
import com.mec.simec_rh.Repositories.HistoricoCarreiraRepositoryInterface;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService  {



    private final FuncionarioRepositoryInterface funcionarioRepo;
    private  final AlocacaoRepositoryInterface alocacaoRepo;
    private  final FormacaoRepositoryInterface formacaoRepo;
    private  final HistoricoCarreiraRepositoryInterface historicoCarreiraRepo;


    public FuncionarioService(FuncionarioRepositoryInterface funcionrioRepo, AlocacaoRepositoryInterface alocacaoRepo, FormacaoRepositoryInterface formacaoRepo, HistoricoCarreiraRepositoryInterface historicoCarreiraRepo) {
        this.funcionarioRepo = funcionrioRepo;
        this.alocacaoRepo = alocacaoRepo;
        this.formacaoRepo = formacaoRepo;
        this.historicoCarreiraRepo = historicoCarreiraRepo;
    }


    public Funcionario createFuncionario(Funcionario funcionario) {
        return funcionarioRepo.save(funcionario);
    }

    public List<Funcionario> getAllFuncionario() {
        return funcionarioRepo.findAll();
    }


    @Transactional
    public Funcionario registrarFuncionario(Funcionario funcionario) {

        // garantir que cada filho conhece o pai
        if (funcionario.getAlocacao() != null) {
            for (Alocacao alocacao : funcionario.getAlocacao()) {
                alocacao.setFuncionario(funcionario);
            }
        }

        if (funcionario.getHistoricoCarreiraList() != null) {
            for (HistoricoCarreira historico : funcionario.getHistoricoCarreiraList()) {
                historico.setFuncionario(funcionario);
            }
        }

        if (funcionario.getFormacaoList() != null) {
            for (Formacao formacao : funcionario.getFormacaoList()) {
                formacao.setFuncionario(funcionario);
            }
        }

        // Agora o JPA sabe as FKs antes de persistir
        return funcionarioRepo.save(funcionario);
    }


    public Funcionario atualizarFuncionario(Funcionario funcionario) {
        return funcionarioRepo.
    }
}
