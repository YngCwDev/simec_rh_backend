package com.mec.simec_rh.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mec.simec_rh.Enums.*;
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
@Table(name = "situacao_de_quadro")
public class SituacaoQuadro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private LocalDate dataDespachoSituacao;
    private String regimeEspecialActividade;
    private LocalDate dataLicenca;
    private LocalDate dataAnotacaoLicenca;
    private String numeroProcessoLicenca;
    private LocalDate dataDespachoCessacao;
    private LocalDate dataAnotacaoCessacao;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    @JsonBackReference("funcionario-situacao")
    private Funcionario funcionario;

    private TipoNomeacao tipoNomeacao;

    private TipoSituacaoQuadro tipoSituacaoQuadro;

    private Licenca licenca;

    private TipoCessacao tipoCessacao;

    private TipoSancaoDisciplinar tipoSancaoDisciplinar;


    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;
}
