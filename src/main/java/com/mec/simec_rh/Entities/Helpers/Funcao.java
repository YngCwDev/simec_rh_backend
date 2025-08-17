package com.mec.simec_rh.Entities.Helpers;

import com.mec.simec_rh.Entities.Alocacao;
import com.mec.simec_rh.Entities.HistoricoCarreira;
import com.mec.simec_rh.Enums.CategoriaFuncao;
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
public class Funcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    private CategoriaFuncao categoriaFuncao;

    private String descricao;

    private boolean ativa;

    @OneToMany(mappedBy = "funcao", cascade = CascadeType.ALL)
    private List<HistoricoCarreira> historicoCarreiraList;

    @OneToMany(mappedBy = "funcao",cascade = CascadeType.ALL)
    private List<Alocacao> alocacaoList;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
