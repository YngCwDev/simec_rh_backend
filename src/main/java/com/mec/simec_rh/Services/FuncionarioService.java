package com.mec.simec_rh.Services;

import com.mec.simec_rh.DTOs.FormacaoDTO;
import com.mec.simec_rh.DTOs.Funcionario.FuncionarioDTO;
import com.mec.simec_rh.DTOs.Funcionario.FuncionarioFullDTO;
import com.mec.simec_rh.Entities.Formacao;
import com.mec.simec_rh.Entities.Funcionario;
import com.mec.simec_rh.Entities.Helpers.Provincia;
import com.mec.simec_rh.Mappers.*;
import com.mec.simec_rh.Repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepositoryInterface funcionarioRepo;
    private final FuncionarioMapper funcionarioMapper;
    private final FormacaoRepositoryInterface formacaoRepo;
    private final HistoricoCarreiraRepositoryInterface historicoRepo;
    private final AlocacaoRepositoryInterface alocacaoRepo;
    private final SituacaoQuadroRepositoryInterface sqRepo;

    private final FuncionarioMapper funcionarioMapper;
    private final FormacaoMapper formacaoMapper;
    private final HistoricoCarreiraMapper historicoMapper;
    private final AlocacaoMapper alocacaoMapper;
    private final SituacaoQuadroMapper sqMapper;

    public FuncionarioService(FuncionarioRepositoryInterface funcionarioRepo, FuncionarioMapper funcionarioMapper, FormacaoRepositoryInterface formacaoRepo, HistoricoCarreiraRepositoryInterface historicoRepo, AlocacaoRepositoryInterface alocacaoRepo, SituacaoQuadroRepositoryInterface sqRepo, FormacaoMapper formacaoMapper, HistoricoCarreiraMapper historicoMapper, AlocacaoMapper alocacaoMapper, SituacaoQuadroMapper sqMapper) {
        this.funcionarioRepo = funcionarioRepo;
        this.funcionarioMapper = funcionarioMapper;
        this.formacaoRepo = formacaoRepo;
        this.historicoRepo = historicoRepo;
        this.alocacaoRepo = alocacaoRepo;
        this.sqRepo = sqRepo;
        this.formacaoMapper = formacaoMapper;
        this.historicoMapper = historicoMapper;
        this.alocacaoMapper = alocacaoMapper;
        this.sqMapper = sqMapper;
    }


    public Funcionario createFuncionario(Funcionario funcionario) {
        return funcionarioRepo.save(funcionario);
    }

    public List<FuncionarioDTO> getAllFullFuncionario() {
        return funcionarioRepo.findAll()
                .stream()
                .map(funcionarioMapper::toDto)
                .toList();
    }


    public FuncionarioFullDTO getAllFuncionario(Long id) {
        Funcionario fun = funcionarioRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Funcionario nao encontrado!"));
        return funcionarioMapper.toFullDto(fun);
    }



    @Transactional
    public FuncionarioFullDTO cadastrarFull(FuncionarioFullDTO dto) {
        // 1) Mapeia só campos simples do funcionario
        Funcionario f = funcionarioMapper.toEntity(dto);

        // 2) Provincia
        if (dto.provinciaNascimentoId() != null) {
            Provincia p = provinciaRepo.findById(dto.provinciaNascimentoId())
                    .orElseThrow(() -> new EntityNotFoundException("Província não encontrada"));
            f.setProvinciaNascimento(p);
        }

        // 3) Persiste o pai primeiro
        f = funcionarioRepo.save(f);

        // 4) Filhos (se vierem) — independentes, mas vinculados ao pai
        List<Formacao> formacoes = new ArrayList<>();
        if (dto.formacoes() != null) {
            for (FormacaoDTO fd : dto.formacoes()) {
                Formacao fe = formacaoMapper.toEntity(fd);
                fe.setFuncionario(f);
                formacoes.add(formacaoRepo.save(fe));
            }
        }
        f.setFormacaoList(formacoes);

        List<HistoricoCarreira> historicos = new ArrayList<>();
        if (dto.historicos() != null) {
            for (HistoricoCarreiraDTO hd : dto.historicos()) {
                HistoricoCarreira he = historicoMapper.toEntity(hd);
                he.setFuncionario(f);
                historicos.add(historicoRepo.save(he));
            }
        }
        f.setHistoricoCarreiraList(historicos);

        List<Alocacao> alocacoes = new ArrayList<>();
        if (dto.alocacoes() != null) {
            for (AlocacaoDTO ad : dto.alocacoes()) {
                Alocacao ae = alocacaoMapper.toEntity(ad);
                ae.setFuncionario(f);
                alocacoes.add(alocacaoRepo.save(ae));
            }
        }
        f.setAlocacaoList(alocacoes);

        List<SituacaoQuadro> sList = new ArrayList<>();
        if (dtoclass.situacoesQuadro() != null) {
            for (SituacaoQuadroDTO sd : dto.situacoesQuadro()) {
                SituacaoQuadro se = sqMapper.toEntity(sd);
                se.setFuncionario(f);
                sList.add(sqRepo.save(se));
            }
        }
        f.setSituacaoQuadroList(sList);

        return funcionarioMapper.toFullDto(f);
    }

    public List<FuncionarioResumoDTO> listarAtivos() {
        return funcionarioRepo.findByAtivoTrue().stream()
                .map(funcionarioMapper::toResumoDto)
                .toList();
    }

    public FuncionarioFullDTO obterFull(Long id) {
        Funcionario f = funcionarioRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));
        return funcionarioMapper.toFullDto(f);
    }

    @Transactional
    public void softDelete(Long id) {
        Funcionario f = funcionarioRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));
        f.setAtivo(false);
        funcionarioRepo.save(f);
    }


}

