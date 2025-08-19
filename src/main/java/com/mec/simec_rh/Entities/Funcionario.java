package com.mec.simec_rh.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mec.simec_rh.Entities.Helpers.Provincia;
import com.mec.simec_rh.Enums.EstadoCivil;
import com.mec.simec_rh.Enums.Sexo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "funcionarios")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="provincia_nascimento_id")
    @JsonBackReference("provincia-funcionario")
    private Provincia provinciaNascimento;

    @OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("funcionario-usuario")
    private  Usuario usuario;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("funcionario-situacao")
    private List<SituacaoQuadro> situacaoQuadroList;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("funcionario-formacao")
    private List<Formacao> formacaoList;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("funcionario-historico")
    private  List<HistoricoCarreira> historicoCarreiraList;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("funcionario-alocacoes")
    private  List<Alocacao> alocacao;


    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;
}
