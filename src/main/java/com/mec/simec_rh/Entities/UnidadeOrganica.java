package com.mec.simec_rh.Entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mec.simec_rh.Enums.TipoUnidadeOrganica;
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
@Table(name = "unidades_organicas")
public class UnidadeOrganica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String nome;
    private String sigla;
    private TipoUnidadeOrganica tipoUnidadeOrganica;

    private String endereco;
    private  int capacidadeFuncionarios;

    @OneToMany(mappedBy = "unidadeOrganica")
    @JsonManagedReference("uniorganica-alocacoes")
    private List<Alocacao> alocacaoList;

    @Column(columnDefinition = "boolean default true")
    private  boolean ativa;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
