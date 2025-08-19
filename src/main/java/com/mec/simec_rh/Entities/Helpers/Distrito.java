package com.mec.simec_rh.Entities.Helpers;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mec.simec_rh.Entities.Alocacao;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
@Table(name = "distritos")
public class Distrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    @JsonBackReference("provincia-distrito")
    private Provincia provincia;

    @OneToMany(mappedBy = "distrito", fetch = FetchType.LAZY)
    @JsonManagedReference("distrito-alocacoes")
    private List<Alocacao> alocacaoList;

    @Column(nullable = false)
    private  boolean ativo = true;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;

}
