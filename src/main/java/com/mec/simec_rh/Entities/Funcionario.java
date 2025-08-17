package com.mec.simec_rh.Entities;

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

    @Column(nullable = false, columnDefinition = "enum default 'Mocambicana'")
    private String nacionalidade;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private int idade;

    @Column(nullable = false)
    private String contactoPrincipal;

    @Column(nullable = false)
    private String contactoSecundario;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate dataIngressoAE;

    @Column(nullable = false)
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

    @OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private  Alocacao alocacao;


    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;
}
