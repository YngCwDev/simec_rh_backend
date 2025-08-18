package com.mec.simec_rh.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mec.simec_rh.Entities.Helpers.Carreira;
import com.mec.simec_rh.Entities.Helpers.Categoria;
import com.mec.simec_rh.Entities.Helpers.Funcao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
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
    @JoinColumn(name="funcionario_id")
    @JsonIgnore
    private Funcionario funcionario;

    @ManyToOne()
    @JoinColumn(name = "funcao_id")
    private Funcao funcao;

    @ManyToOne()
    @JoinColumn(name = "carreira_id")
    private Carreira carreira;

    @ManyToOne()
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
