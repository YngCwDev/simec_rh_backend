package com.mec.simec_rh.Entities;

import com.mec.simec_rh.Entities.Helpers.*;
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
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "tipo_nomeacao_id")
    private TipoNomeacao tipoNomeacao;

    @ManyToOne
    @JoinColumn(name = "tipo_situacao_quadro_id")
    private TipoSituacaoQuadro tipoSituacaoQuadro;

    @ManyToOne
    @JoinColumn(name = "tipo_licenca_id")
    private TipoLicenca tipoLicenca;

    @ManyToOne
    @JoinColumn(name = "tipo_cessacao_id")
    private TipoCessacao tipoCessacao;

    @ManyToOne
    @JoinColumn(name = "tipo_sancao_disciplinar_id")
    private TipoSancaoDisciplinar tipoSancaoDisciplinar;


    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;
}
