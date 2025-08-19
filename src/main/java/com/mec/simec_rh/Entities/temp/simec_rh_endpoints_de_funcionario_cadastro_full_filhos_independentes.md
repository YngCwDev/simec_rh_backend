// =============================================================
//  PROJETO: SIMEC RH — Funcionario + Filhos (modelo híbrido)
//  - Cadastro inicial "full" (pai + filhos via 1 payload)
//  - CRUD independente para Formacao, HistoricoCarreira, Alocacao, SituacaoQuadro
//  - Soft delete (nunca apaga de verdade; marca ativo=false)
//  - DTOs com MapStruct + Controllers + Services + Repos
//  - Datas em dd/MM/yyyy
// =============================================================

// -------------------------------------------------------------
// build.gradle (ou pom.xml) — dependências essenciais
// -------------------------------------------------------------
/*
// build.gradle (trecho)
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'mysql:mysql-connector-j'
    implementation 'org.mapstruct:mapstruct:1.5.5.Final'
    annotationProcessor 'org.mapstruct:mapstruct-processor:1.5.5.Final'
    implementation 'com.fasterxml.jackson.datatype:jackson-datatype-jsr310'

    // Lombok
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
}
*/

// -------------------------------------------------------------
// Configs/JacksonConfig.java — formato de data dd/MM/yyyy
// -------------------------------------------------------------
package com.mec.simec_rh.Entities.temp;

import java.time.LocalDate;

//@Configuration
//public class JacksonConfig {
//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        JavaTimeModule module = new JavaTimeModule();
//        module.addDeserializer(LocalDate.class,
//                new LocalDateDeserializer(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//        mapper.registerModule(module);
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        return mapper;
//    }
//}

// -------------------------------------------------------------
// Entities (apenas campos relevantes para o fluxo; ajuste aos seus)
// Evitamos recursão com @JsonManagedReference / @JsonBackReference
// e mantemos soft-delete via campo "ativo".
// -------------------------------------------------------------
package com.mec.simec_rh.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Funcionario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nuit;
    private String numeroCracha;
    private String nomeCompleto;
    private String numeroBi;
    private String sexo; // pode ser enum
    private String estadoCivil; // pode ser enum
    private String nacionalidade;
    private LocalDate dataNascimento;
    private Integer idade;
    private String contactoPrincipal;
    private String contactoSecundario;
    private String email;
    private LocalDate dataIngressoAE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provincia_nascimento_id")
    private Provincia provinciaNascimento;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("funcionario-formacoes")
    private List<Formacao> formacaoList = new ArrayList<>();

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("funcionario-historicos")
    private List<HistoricoCarreira> historicoCarreiraList = new ArrayList<>();

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("funcionario-alocacoes")
    private List<Alocacao> alocacaoList = new ArrayList<>(); // histórico de alocações

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("funcionario-sq")
    private List<SituacaoQuadro> situacaoQuadroList = new ArrayList<>();

    private boolean ativo = true; // soft delete

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Formacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nivelEducacional; // enum na prática
    private LocalDate dataConclusao;
    private String especialidade;
    private String instituicaoEnsino;
    private String anoConclusao; // se for realmente ano, prefira Integer
    private String formacaoGeral;
    private String formacaoTecnica;
    private String formacaoEducacional;
    private String formacaoMaisElevada; // enum

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id")
    @JsonBackReference("funcionario-formacoes")
    private Funcionario funcionario;

    private boolean ativo = true;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class HistoricoCarreira {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accao; // enum
    private String classe; // A/B/C/E
    private String escalao; // 1..4 (string ou int)
    private String numeroProcessoVTAFuncao;
    private LocalDate dataVTAClasse;
    private LocalDate dataVTACarreira;
    private LocalDate dataAnotacaoEscalao;
    private LocalDate dataDespachoFuncao;
    private LocalDate despachoCessacaoFuncao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id")
    @JsonBackReference("funcionario-historicos")
    private Funcionario funcionario;

    private boolean ativo = true;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Alocacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String classe; // A/B/C/E
    private String escalao; // 1..4
    private LocalDate dataIncioAlocacao;
    private LocalDate dataFinalAlocacao; // null = atual
    private String statusAlocacao; // enum
    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id")
    @JsonBackReference("funcionario-alocacoes")
    private Funcionario funcionario;

    private boolean ativo = true;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class SituacaoQuadro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataDespachoSituacao;
    private String regimeEspecialActividade; // enum
    private LocalDate dataLicenca;
    private LocalDate dataAnotacaoLicenca;
    private String numeroProcessoLicenca;
    private LocalDate dataDespachoCessacao;
    private LocalDate dataAnotacaoCessacao;
    private String observacoes;

    // tipos (nomeacao, licenca, cessacao, sancao) podem ser enums/refs — omitido aqui

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id")
    @JsonBackReference("funcionario-sq")
    private Funcionario funcionario;

    private boolean ativo = true;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Provincia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sigla;
    private String regiao;

    private boolean ative = true;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

// -------------------------------------------------------------
// DTOs — records (entrada/saída). Para cadastro full e CRUDs.
// -------------------------------------------------------------
package com.mec.simec_rh.DTOS;

import java.time.LocalDate;
import java.util.List;

public record FuncionarioFullDTO(
        Long id,
        String nuit,
        String numeroCracha,
        String nomeCompleto,
        String numeroBi,
        String sexo,
        String estadoCivil,
        String nacionalidade,
        LocalDate dataNascimento,
        Integer idade,
        String contactoPrincipal,
        String contactoSecundario,
        String email,
        LocalDate dataIngressoAE,
        Long provinciaNascimentoId,
        List<FormacaoDTO> formacoes,
        List<HistoricoCarreiraDTO> historicos,
        List<AlocacaoDTO> alocacoes,
        List<SituacaoQuadroDTO> situacoesQuadro,
        Boolean ativo
) {}

public record FuncionarioResumoDTO(
        Long id,
        String nomeCompleto,
        String numeroCracha,
        String email,
        Boolean ativo
) {}

public record FormacaoDTO(
        Long id,
        String nivelEducacional,
        LocalDate dataConclusao,
        String especialidade,
        String instituicaoEnsino,
        String anoConclusao,
        String formacaoGeral,
        String formacaoTecnica,
        String formacaoEducacional,
        String formacaoMaisElevada,
        Long funcionarioId,
        Boolean ativo
) {}

public record HistoricoCarreiraDTO(
        Long id,
        String accao,
        String classe,
        String escalao,
        String numeroProcessoVTAFuncao,
        LocalDate dataVTAClasse,
        LocalDate dataVTACarreira,
        LocalDate dataAnotacaoEscalao,
        LocalDate dataDespachoFuncao,
        LocalDate despachoCessacaoFuncao,
        Long funcionarioId,
        Boolean ativo
) {}

public record AlocacaoDTO(
        Long id,
        String classe,
        String escalao,
        LocalDate dataIncioAlocacao,
        LocalDate dataFinalAlocacao,
        String statusAlocacao,
        String observacoes,
        Long funcionarioId,
        Boolean ativo
) {}

public record SituacaoQuadroDTO(
        Long id,
        LocalDate dataDespachoSituacao,
        String regimeEspecialActividade,
        LocalDate dataLicenca,
        LocalDate dataAnotacaoLicenca,
        String numeroProcessoLicenca,
        LocalDate dataDespachoCessacao,
        LocalDate dataAnotacaoCessacao,
        String observacoes,
        Long funcionarioId,
        Boolean ativo
) {}

// -------------------------------------------------------------
// MapStruct Mappers — com @AfterMapping para setar o pai
// -------------------------------------------------------------
package com.mec.simec_rh.Mappers;

import com.mec.simec_rh.DTOS.*;
import com.mec.simec_rh.Entities.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FormacaoMapper {
    FormacaoDTO toDto(Formacao e);
    Formacao toEntity(FormacaoDTO d);
}

@Mapper(componentModel = "spring")
public interface HistoricoCarreiraMapper {
    HistoricoCarreiraDTO toDto(HistoricoCarreira e);
    HistoricoCarreira toEntity(HistoricoCarreiraDTO d);
}

@Mapper(componentModel = "spring")
public interface AlocacaoMapper {
    AlocacaoDTO toDto(Alocacao e);
    Alocacao toEntity(AlocacaoDTO d);
}

@Mapper(componentModel = "spring")
public interface SituacaoQuadroMapper {
    SituacaoQuadroDTO toDto(SituacaoQuadro e);
    SituacaoQuadro toEntity(SituacaoQuadroDTO d);
}

@Mapper(componentModel = "spring", uses = {FormacaoMapper.class, HistoricoCarreiraMapper.class, AlocacaoMapper.class, SituacaoQuadroMapper.class})
public interface FuncionarioMapper {

    @Mapping(target = "provinciaNascimentoId", source = "provinciaNascimento.id")
    @Mapping(target = "formacoes", source = "formacaoList")
    @Mapping(target = "historicos", source = "historicoCarreiraList")
    @Mapping(target = "alocacoes", source = "alocacaoList")
    @Mapping(target = "situacoesQuadro", source = "situacaoQuadroList")
    FuncionarioFullDTO toFullDto(Funcionario e);

    @Mapping(target = "nomeCompleto", source = "nomeCompleto")
    FuncionarioResumoDTO toResumoDto(Funcionario e);

    // Para DTO -> Entity (cadastro full)
    @Mapping(target = "provinciaNascimento", ignore = true) // resolvemos no service
    @Mapping(target = "formacaoList", ignore = true)
    @Mapping(target = "historicoCarreiraList", ignore = true)
    @Mapping(target = "alocacaoList", ignore = true)
    @Mapping(target = "situacaoQuadroList", ignore = true)
    Funcionario toEntity(FuncionarioFullDTO d);
}

// -------------------------------------------------------------
// Repositories
// -------------------------------------------------------------
package com.mec.simec_rh.Repositories;

import com.mec.simec_rh.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByAtivoTrue();
}

public interface FormacaoRepository extends JpaRepository<Formacao, Long> {
    List<Formacao> findByFuncionarioIdAndAtivoTrue(Long funcionarioId);
}

public interface HistoricoCarreiraRepository extends JpaRepository<HistoricoCarreira, Long> {
    List<HistoricoCarreira> findByFuncionarioIdAndAtivoTrue(Long funcionarioId);
}

public interface AlocacaoRepository extends JpaRepository<Alocacao, Long> {
    List<Alocacao> findByFuncionarioIdAndAtivoTrue(Long funcionarioId);
}

public interface SituacaoQuadroRepository extends JpaRepository<SituacaoQuadro, Long> {
    List<SituacaoQuadro> findByFuncionarioIdAndAtivoTrue(Long funcionarioId);
}

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {}

// -------------------------------------------------------------
// Services — Regra: soft delete, cadastro full, CRUD individual
// -------------------------------------------------------------
package com.mec.simec_rh.Services;

import com.mec.simec_rh.DTOS.*;
import com.mec.simec_rh.Entities.*;
import com.mec.simec_rh.Mappers.*;
import com.mec.simec_rh.Repositories.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepo;
    private final ProvinciaRepository provinciaRepo;

    private final FormacaoRepository formacaoRepo;
    private final HistoricoCarreiraRepository historicoRepo;
    private final AlocacaoRepository alocacaoRepo;
    private final SituacaoQuadroRepository sqRepo;

    private final FuncionarioMapper funcionarioMapper;
    private final FormacaoMapper formacaoMapper;
    private final HistoricoCarreiraMapper historicoMapper;
    private final AlocacaoMapper alocacaoMapper;
    private final SituacaoQuadroMapper sqMapper;

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

    @Transactional
    public FuncionarioFullDTO atualizarParcial(Long id, FuncionarioFullDTO dto) {
        Funcionario f = funcionarioRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));

        if (dto.nuit() != null) f.setNuit(dto.nuit());
        if (dto.numeroCracha() != null) f.setNumeroCracha(dto.numeroCracha());
        if (dto.nomeCompleto() != null) f.setNomeCompleto(dto.nomeCompleto());
        if (dto.numeroBi() != null) f.setNumeroBi(dto.numeroBi());
        if (dto.sexo() != null) f.setSexo(dto.sexo());
        if (dto.estadoCivil() != null) f.setEstadoCivil(dto.estadoCivil());
        if (dto.nacionalidade() != null) f.setNacionalidade(dto.nacionalidade());
        if (dto.dataNascimento() != null) f.setDataNascimento(dto.dataNascimento());
        if (dto.idade() != null) f.setIdade(dto.idade());
        if (dto.contactoPrincipal() != null) f.setContactoPrincipal(dto.contactoPrincipal());
        if (dto.contactoSecundario() != null) f.setContactoSecundario(dto.contactoSecundario());
        if (dto.email() != null) f.setEmail(dto.email());
        if (dto.dataIngressoAE() != null) f.setDataIngressoAE(dto.dataIngressoAE());
        if (dto.ativo() != null) f.setAtivo(dto.ativo());
        if (dto.provinciaNascimentoId() != null) {
            Provincia p = provinciaRepo.findById(dto.provinciaNascimentoId())
                    .orElseThrow(() -> new EntityNotFoundException("Província não encontrada"));
            f.setProvinciaNascimento(p);
        }
        // Não atualizamos filhos aqui (CRUD independente)
        return funcionarioMapper.toFullDto(funcionarioRepo.save(f));
    }
}

@Service
@RequiredArgsConstructor
public class FormacaoService {
    private final FormacaoRepository repo;
    private final FuncionarioRepository funcionarioRepo;
    private final FormacaoMapper mapper;

    @Transactional
    public FormacaoDTO criar(FormacaoDTO d) {
        Funcionario f = funcionarioRepo.findById(d.funcionarioId())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));
        Formacao e = mapper.toEntity(d);
        e.setFuncionario(f);
        e = repo.save(e);
        return mapper.toDto(e);
    }

    @Transactional
    public FormacaoDTO atualizar(Long id, FormacaoDTO d) {
        Formacao e = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Formação não encontrada"));
        if (d.nivelEducacional() != null) e.setNivelEducacional(d.nivelEducacional());
        if (d.dataConclusao() != null) e.setDataConclusao(d.dataConclusao());
        if (d.especialidade() != null) e.setEspecialidade(d.especialidade());
        if (d.instituicaoEnsino() != null) e.setInstituicaoEnsino(d.instituicaoEnsino());
        if (d.anoConclusao() != null) e.setAnoConclusao(d.anoConclusao());
        if (d.formacaoGeral() != null) e.setFormacaoGeral(d.formacaoGeral());
        if (d.formacaoTecnica() != null) e.setFormacaoTecnica(d.formacaoTecnica());
        if (d.formacaoEducacional() != null) e.setFormacaoEducacional(d.formacaoEducacional());
        if (d.formacaoMaisElevada() != null) e.setFormacaoMaisElevada(d.formacaoMaisElevada());
        if (d.ativo() != null) e.setAtivo(d.ativo());
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void softDelete(Long id) {
        Formacao e = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Formação não encontrada"));
        e.setAtivo(false);
        repo.save(e);
    }
}

@Service
@RequiredArgsConstructor
public class HistoricoCarreiraService {
    private final HistoricoCarreiraRepository repo;
    private final FuncionarioRepository funcionarioRepo;
    private final HistoricoCarreiraMapper mapper;

    @Transactional
    public HistoricoCarreiraDTO criar(HistoricoCarreiraDTO d) {
        Funcionario f = funcionarioRepo.findById(d.funcionarioId())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));
        HistoricoCarreira e = mapper.toEntity(d);
        e.setFuncionario(f);
        e = repo.save(e);
        return mapper.toDto(e);
    }

    @Transactional
    public HistoricoCarreiraDTO atualizar(Long id, HistoricoCarreiraDTO d) {
        HistoricoCarreira e = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Histórico não encontrado"));
        if (d.accao() != null) e.setAccao(d.accao());
        if (d.classe() != null) e.setClasse(d.classe());
        if (d.escalao() != null) e.setEscalao(d.escalao());
        if (d.numeroProcessoVTAFuncao() != null) e.setNumeroProcessoVTAFuncao(d.numeroProcessoVTAFuncao());
        if (d.dataVTAClasse() != null) e.setDataVTAClasse(d.dataVTAClasse());
        if (d.dataVTACarreira() != null) e.setDataVTACarreira(d.dataVTACarreira());
        if (d.dataAnotacaoEscalao() != null) e.setDataAnotacaoEscalao(d.dataAnotacaoEscalao());
        if (d.dataDespachoFuncao() != null) e.setDataDespachoFuncao(d.dataDespachoFuncao());
        if (d.despachoCessacaoFuncao() != null) e.setDespachoCessacaoFuncao(d.despachoCessacaoFuncao());
        if (d.ativo() != null) e.setAtivo(d.ativo());
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void softDelete(Long id) {
        HistoricoCarreira e = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Histórico não encontrado"));
        e.setAtivo(false);
        repo.save(e);
    }
}

@Service
@RequiredArgsConstructor
public class AlocacaoService {
    private final AlocacaoRepository repo;
    private final FuncionarioRepository funcionarioRepo;
    private final AlocacaoMapper mapper;

    @Transactional
    public AlocacaoDTO criar(AlocacaoDTO d) {
        Funcionario f = funcionarioRepo.findById(d.funcionarioId())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));
        Alocacao e = mapper.toEntity(d);
        e.setFuncionario(f);
        e = repo.save(e);
        return mapper.toDto(e);
    }

    @Transactional
    public AlocacaoDTO atualizar(Long id, AlocacaoDTO d) {
        Alocacao e = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alocação não encontrada"));
        if (d.classe() != null) e.setClasse(d.classe());
        if (d.escalao() != null) e.setEscalao(d.escalao());
        if (d.dataIncioAlocacao() != null) e.setDataIncioAlocacao(d.dataIncioAlocacao());
        if (d.dataFinalAlocacao() != null) e.setDataFinalAlocacao(d.dataFinalAlocacao());
        if (d.statusAlocacao() != null) e.setStatusAlocacao(d.statusAlocacao());
        if (d.observacoes() != null) e.setObservacoes(d.observacoes());
        if (d.ativo() != null) e.setAtivo(d.ativo());
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void softDelete(Long id) {
        Alocacao e = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alocação não encontrada"));
        e.setAtivo(false);
        repo.save(e);
    }
}

@Service
@RequiredArgsConstructor
public class SituacaoQuadroService {
    private final SituacaoQuadroRepository repo;
    private final FuncionarioRepository funcionarioRepo;
    private final SituacaoQuadroMapper mapper;

    @Transactional
    public SituacaoQuadroDTO criar(SituacaoQuadroDTO d) {
        Funcionario f = funcionarioRepo.findById(d.funcionarioId())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));
        SituacaoQuadro e = mapper.toEntity(d);
        e.setFuncionario(f);
        e = repo.save(e);
        return mapper.toDto(e);
    }

    @Transactional
    public SituacaoQuadroDTO atualizar(Long id, SituacaoQuadroDTO d) {
        SituacaoQuadro e = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Situação do Quadro não encontrada"));
        if (d.dataDespachoSituacao() != null) e.setDataDespachoSituacao(d.dataDespachoSituacao());
        if (d.regimeEspecialActividade() != null) e.setRegimeEspecialActividade(d.regimeEspecialActividade());
        if (d.dataLicenca() != null) e.setDataLicenca(d.dataLicenca());
        if (d.dataAnotacaoLicenca() != null) e.setDataAnotacaoLicenca(d.dataAnotacaoLicenca());
        if (d.numeroProcessoLicenca() != null) e.setNumeroProcessoLicenca(d.numeroProcessoLicenca());
        if (d.dataDespachoCessacao() != null) e.setDataDespachoCessacao(d.dataDespachoCessacao());
        if (d.dataAnotacaoCessacao() != null) e.setDataAnotacaoCessacao(d.dataAnotacaoCessacao());
        if (d.observacoes() != null) e.setObservacoes(d.observacoes());
        if (d.ativo() != null) e.setAtivo(d.ativo());
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void softDelete(Long id) {
        SituacaoQuadro e = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Situação do Quadro não encontrada"));
        e.setAtivo(false);
        repo.save(e);
    }
}

// -------------------------------------------------------------
// Controllers — Rotas públicas (sem auth por enquanto)
// -------------------------------------------------------------
package com.mec.simec_rh.Controllers;

import com.mec.simec_rh.DTOS.*;
import com.mec.simec_rh.Services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {
    private final FuncionarioService service;

    @PostMapping
    public ResponseEntity<FuncionarioFullDTO> cadastrarFull(@RequestBody FuncionarioFullDTO dto) {
        FuncionarioFullDTO saved = service.cadastrarFull(dto);
        return ResponseEntity.created(URI.create("/api/funcionarios/" + saved.id())).body(saved);
    }

    @GetMapping
    public List<FuncionarioResumoDTO> listar() {
        return service.listarAtivos();
    }

    @GetMapping("/{id}")
    public FuncionarioFullDTO obter(@PathVariable Long id) { return service.obterFull(id); }

    @PatchMapping("/{id}")
    public FuncionarioFullDTO atualizarParcial(@PathVariable Long id, @RequestBody FuncionarioFullDTO dto) {
        return service.atualizarParcial(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}

@RestController
@RequestMapping("/api/formacoes")
@RequiredArgsConstructor
class FormacaoController {
    private final FormacaoService service;

    @PostMapping
    public ResponseEntity<FormacaoDTO> criar(@RequestBody FormacaoDTO d) {
        FormacaoDTO saved = service.criar(d);
        return ResponseEntity.created(URI.create("/api/formacoes/" + saved.id())).body(saved);
    }

    @PutMapping("/{id}")
    public FormacaoDTO atualizar(@PathVariable Long id, @RequestBody FormacaoDTO d) {
        return service.atualizar(id, d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}

@RestController
@RequestMapping("/api/historicos")
@RequiredArgsConstructor
class HistoricoCarreiraController {
    private final HistoricoCarreiraService service;

    @PostMapping
    public ResponseEntity<HistoricoCarreiraDTO> criar(@RequestBody HistoricoCarreiraDTO d) {
        HistoricoCarreiraDTO saved = service.criar(d);
        return ResponseEntity.created(URI.create("/api/historicos/" + saved.id())).body(saved);
    }

    @PutMapping("/{id}")
    public HistoricoCarreiraDTO atualizar(@PathVariable Long id, @RequestBody HistoricoCarreiraDTO d) {
        return service.atualizar(id, d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}

@RestController
@RequestMapping("/api/alocacoes")
@RequiredArgsConstructor
class AlocacaoController {
    private final AlocacaoService service;

    @PostMapping
    public ResponseEntity<AlocacaoDTO> criar(@RequestBody AlocacaoDTO d) {
        AlocacaoDTO saved = service.criar(d);
        return ResponseEntity.created(URI.create("/api/alocacoes/" + saved.id())).body(saved);
    }

    @PutMapping("/{id}")
    public AlocacaoDTO atualizar(@PathVariable Long id, @RequestBody AlocacaoDTO d) {
        return service.atualizar(id, d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}

@RestController
@RequestMapping("/api/situacoes-quadro")
@RequiredArgsConstructor
class SituacaoQuadroController {
    private final SituacaoQuadroService service;

    @PostMapping
    public ResponseEntity<SituacaoQuadroDTO> criar(@RequestBody SituacaoQuadroDTO d) {
        SituacaoQuadroDTO saved = service.criar(d);
        return ResponseEntity.created(URI.create("/api/situacoes-quadro/" + saved.id())).body(saved);
    }

    @PutMapping("/{id}")
    public SituacaoQuadroDTO atualizar(@PathVariable Long id, @RequestBody SituacaoQuadroDTO d) {
        return service.atualizar(id, d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}

// -------------------------------------------------------------
// Exemplos de payload
// -------------------------------------------------------------
/*
POST /api/funcionarios
{
  "nuit": "789456123",
  "numeroCracha": "CRCH2025A01",
  "nomeCompleto": "Maria Fernanda",
  "numeroBi": "8765432198765",
  "sexo": "F",
  "estadoCivil": "CASADO",
  "nacionalidade": "Mocambicana",
  "dataNascimento": "15/05/1998",
  "idade": 27,
  "contactoPrincipal": "842223344",
  "contactoSecundario": "876554433",
  "email": "maria.fernanda@example.com",
  "dataIngressoAE": "01/02/2020",
  "provinciaNascimentoId": 1,
  "formacoes": [
    {
      "nivelEducacional": "MESTRADO",
      "dataConclusao": "30/11/2023",
      "especialidade": "Gestão Pública",
      "instituicaoEnsino": "UEM",
      "anoConclusao": "2023",
      "formacaoGeral": "Ciências Sociais",
      "formacaoTecnica": "Administração",
      "formacaoEducacional": "Ensino Superior",
      "formacaoMaisElevada": "MESTRADO"
    }
  ],
  "historicos": [
    {
      "accao": "Nomeado",
      "classe": "C",
      "escalao": "2",
      "numeroProcessoVTAFuncao": "FUNC2025-003",
      "dataVTAClasse": "10/03/2021",
      "dataVTACarreira": "10/03/2021",
      "dataAnotacaoEscalao": "15/03/2021",
      "dataDespachoFuncao": "15/03/2021",
      "despachoCessacaoFuncao": "30/11/2024"
    }
  ],
  "alocacoes": [
    {
      "classe": "B",
      "escalao": "3",
      "dataIncioAlocacao": "18/07/2025",
      "dataFinalAlocacao": "18/07/2025",
      "statusAlocacao": "ATIVA",
      "observacoes": "Primeira alocação"
    }
  ],
  "situacoesQuadro": [
    {
      "dataDespachoSituacao": "12/02/2021",
      "regimeEspecialActividade": "Normal",
      "dataLicenca": "12/02/2021",
      "dataAnotacaoLicenca": "15/02/2021",
      "numeroProcessoLicenca": "LIC2025-001",
      "dataDespachoCessacao": "20/12/2024",
      "dataAnotacaoCessacao": "21/12/2024",
      "observacoes": "Nomeação em efetividade"
    }
  ],
  "ativo": true
}
*/
