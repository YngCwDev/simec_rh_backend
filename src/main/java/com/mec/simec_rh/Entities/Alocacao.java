package com.mec.simec_rh.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mec.simec_rh.Entities.Helpers.*;
import com.mec.simec_rh.Enums.Classe;
import com.mec.simec_rh.Enums.StatusAlocacao;
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
@Table(name = "alocacoes")
public class Alocacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Classe classe;
    private int escalao;
    private LocalDate dataIncioAlocacao;
    private LocalDate dataFinalAlocacao;
    private StatusAlocacao statusAlocacao;
    private String observacoes;

    /* Relationships */

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    @JsonBackReference("funcionario-alocacoes")
    private Funcionario funcionario;


    @ManyToOne
    @JoinColumn(name = "unidade_organica_id")
    @JsonBackReference("uniorganica-alocacoes")
    private UnidadeOrganica unidadeOrganica;


    @ManyToOne
    @JoinColumn(name = "provincia_id")
    @JsonBackReference("provincia-alocacoes")
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "distrito_id")
    @JsonBackReference("distrito-alocacoes")
    private Distrito distrito;

    @ManyToOne
    @JoinColumn(name = "carreira_id")
    @JsonBackReference("carreira-alocacoes")
    private Carreira carreira;

    @ManyToOne
    @JoinColumn(name = "funcao_id")
    @JsonBackReference("funcao-alocacoes")
    private Funcao funcao;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonBackReference("categoria-alocacoes")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "alocado_por_id")
    @JsonBackReference("usuario-alocacoes")
    private Usuario alocadoPor;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
