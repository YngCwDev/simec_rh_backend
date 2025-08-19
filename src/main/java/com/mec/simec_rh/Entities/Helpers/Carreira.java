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
@Table(name = "carreiras")
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
    @JsonManagedReference("carreira-alocacoes")
    private List<Alocacao> alocacaoList;

    @OneToMany(mappedBy = "carreira")
    @JsonManagedReference("carreira-historico")
    private  List<HistoricoCarreira> historicoCarreiraList;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;
}
