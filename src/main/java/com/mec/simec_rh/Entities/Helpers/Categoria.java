package com.mec.simec_rh.Entities.Helpers;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mec.simec_rh.Entities.Alocacao;
import com.mec.simec_rh.Entities.HistoricoCarreira;
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
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String nome;

    @OneToMany(mappedBy = "categoria")
    @JsonManagedReference("categoria-alocacoes")
    private List<Alocacao> alocacaoList;

    @OneToMany(mappedBy = "categoria")
    @JsonManagedReference("categoria-historico")
    private List<HistoricoCarreira> historicoCarreiraList;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;
}
