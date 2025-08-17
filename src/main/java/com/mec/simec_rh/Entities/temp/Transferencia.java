package com.mec.simec_rh.Entities.planTo;

import com.mec.simec_rh.Entities.Alocacao;
import com.mec.simec_rh.Entities.Funcionario;
import com.mec.simec_rh.Entities.Helpers.Distrito;
import com.mec.simec_rh.Entities.Helpers.Provincia;
import com.mec.simec_rh.Entities.UnidadeOrganica;
import com.mec.simec_rh.Entities.Usuario;
import com.mec.simec_rh.Enums.StatusTransferencia;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "alocacao_origin_id")
    private Alocacao alocacaoOrigem;

    @ManyToOne
    @JoinColumn(name = "unidade_destino_id")
    private UnidadeOrganica unidadeDestino;

    @ManyToOne
    @JoinColumn(name = "provincia_destino_id")
    private Provincia provinciaDestino;

    @ManyToOne
    @JoinColumn(name = "distrito_destino_id")
    private Distrito distritoDestinoId;

    @ManyToOne
    @JoinColumn(name = "aprovado_por_id")
    private Usuario aprovadoPor;

    private String motivoTransferencia;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataAprovacao;
    private LocalDateTime dataEfetivacao;
    private String numeroDespacho;
    private LocalDateTime dataVTA;
    private String numeroProcessoVTA;

    private StatusTransferencia statusTransferencia;



    private String observacoes;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
