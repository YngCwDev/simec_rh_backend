package com.mec.simec_rh.Entities.Helpers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mec.simec_rh.Entities.Alocacao;
import com.mec.simec_rh.Entities.Funcionario;
import com.mec.simec_rh.Enums.Regiao;
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
@Table(name = "provincias")
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(unique = true, length = 10)
    private String sigla;

    @Enumerated(EnumType.STRING)
    private Regiao regiao;

    @Column(nullable = false)
    private boolean isAtive;

    @OneToMany(mappedBy = "provinciaNascimento",cascade = CascadeType.ALL)
    @JsonManagedReference("provincia-funcionario")
    private List<Funcionario> funcionarioList;

    @OneToMany(mappedBy = "provincia",cascade = CascadeType.ALL)
    @JsonManagedReference("provincia-alocacoes")
    private List<Alocacao> alocacaoList;


    @OneToMany(mappedBy = "provincia",cascade = CascadeType.ALL)
    @JsonManagedReference("provincia-distrito")
    private  List<Distrito> distritoList;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;
}
