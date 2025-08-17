package com.mec.simec_rh.Entities;


import com.mec.simec_rh.Enums.TipoUnidadeOrganica;
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

    @Column(columnDefinition = "boolean default true")
    private  boolean ativa;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
