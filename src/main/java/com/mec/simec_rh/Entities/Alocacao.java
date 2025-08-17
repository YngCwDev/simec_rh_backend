package com.mec.simec_rh.Entities;

import com.mec.simec_rh.Entities.Helpers.*;
import com.mec.simec_rh.Enums.Classe;
import com.mec.simec_rh.Enums.StatusAlocacao;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Alocacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Classe classe;
    private int escalao;
    private LocalDateTime dataIncioAlocacao;
    private LocalDateTime dataFinalAlocacao;
    private StatusAlocacao statusAlocacao;
    private String observacoes;

    /* Relationships */

    @OneToOne(mappedBy = "alocacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private Funcionario funcionario;

    // unidadeOrganica

    @ManyToOne
    @JoinColumn(name = "unidade_organica_id")
    private UnidadeOrganica unidadeOrganica;

    // provincia

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    // distrito
    @ManyToOne
    @JoinColumn(name = "distrito_id")
    private Distrito distrito;

    // carreira
    @ManyToOne
    @JoinColumn(name = "carreira_id")
    private Carreira carreira;

    // funcao
    @ManyToOne
    @JoinColumn(name = "funcao_id")
    private Funcao funcao;

    // categoria
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // Usuario
    @ManyToOne
    @JoinColumn(name = "alocado_por_id")
    private Usuario alocadoPor;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
