package com.mec.simec_rh.Entities;

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
public class Formacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
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
