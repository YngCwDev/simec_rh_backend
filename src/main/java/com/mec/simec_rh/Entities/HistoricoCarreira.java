package com.mec.simec_rh.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mec.simec_rh.Entities.Helpers.Carreira;
import com.mec.simec_rh.Entities.Helpers.Categoria;
import com.mec.simec_rh.Entities.Helpers.Funcao;
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
@Table(name = "historicos_de_carreira")
public class HistoricoCarreira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String accao;
    private String classe;
    private String escalao;
    private String numeroProcessoVTAFuncao;
    private LocalDate dataVTAClasse;
    private LocalDate dataVTACarreira;
    private LocalDate dataAnotacaoEscalao;
    private LocalDate dataDespachoFuncao;
    private LocalDate despachoCessacaoFuncao;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    @JsonBackReference("funcionario-historico")
    private Funcionario funcionario;

    @ManyToOne()
    @JoinColumn(name = "funcao_id")
    @JsonBackReference("funcao-historico")
    private Funcao funcao;

    @ManyToOne()
    @JoinColumn(name = "carreira_id")
    @JsonBackReference("carreira-historico")
    private Carreira carreira;

    @ManyToOne()
    @JoinColumn(name = "categoria_id")
    @JsonBackReference("categoria-historico")
    private Categoria categoria;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
