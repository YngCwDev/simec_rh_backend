package com.mec.simec_rh.Entities.Helpers;

import com.mec.simec_rh.Entities.SituacaoQuadro;
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
public class TipoLicenca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "tipoLicenca",cascade = CascadeType.ALL)
    private List<SituacaoQuadro> situacaoQuadroList;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
