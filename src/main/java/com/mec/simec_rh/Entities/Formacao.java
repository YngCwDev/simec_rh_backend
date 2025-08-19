package com.mec.simec_rh.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mec.simec_rh.Enums.NivelEducacional;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "formacoes")
public class Formacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    @JsonBackReference("funcionario-formacao")
    private Funcionario funcionario;

    @Enumerated(EnumType.STRING)
    private NivelEducacional nivelEducacional;
    private LocalDate dataConclusao;

    private String especialidade;
    private String instituicaoEnsino;

    private LocalDate anoConclusao;
    private String formacaoGeral;
    private String formacaoTecnica;
    private String formacaoEducacional;
    private String formacaoMaisElevada;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;

}
