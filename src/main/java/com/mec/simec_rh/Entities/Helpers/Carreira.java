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
public class Carreira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private String titulo;

    private String regime;
    private String descricao;
    private boolean isActive;

    @OneToMany(mappedBy = "carreira")
    private List<Alocacao> alocacaoList;

    @OneToMany(mappedBy = "carreira")
    private  List<HistoricoCarreira> historicoCarreiraList;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;
}
