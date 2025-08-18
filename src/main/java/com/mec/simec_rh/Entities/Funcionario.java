package com.mec.simec_rh.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mec.simec_rh.Entities.Helpers.Provincia;
import com.mec.simec_rh.Enums.EstadoCivil;
import com.mec.simec_rh.Enums.Sexo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nuit;

    @Column(unique = true, nullable = false)
    private String numeroCracha;

    @Column(unique = true, nullable = false)
    private String nomeCompleto;

    @Column(unique = true, nullable = false)
    private String numeroBi;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;


    private String nacionalidade;

    private LocalDate dataNascimento;

    private int idade;

    private String contactoPrincipal;

    private String contactoSecundario;

    private String email;

    private LocalDate dataIngressoAE;

    private boolean Ativo;


    /*RelationShips*/
    @ManyToOne
    @JoinColumn(name="provincia_nascimento_id")
    private Provincia provinciaNascimento;

    @OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private  Usuario usuario;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SituacaoQuadro> situacaoQuadroList;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Formacao> formacaoList;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private  List<HistoricoCarreira> historicoCarreiraList;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private  List<Alocacao> alocacao;


    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;
}
