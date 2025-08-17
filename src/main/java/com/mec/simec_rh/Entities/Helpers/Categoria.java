package com.mec.simec_rh.Entities.Helpers;


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
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Alocacao> alocacaoList;

    @OneToMany(mappedBy = "categoria")
    private List<HistoricoCarreira> historicoCarreiraList;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;
}
