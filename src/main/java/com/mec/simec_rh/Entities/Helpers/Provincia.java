package com.mec.simec_rh.Entities.Helpers;

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

    @OneToMany(mappedBy = "provincia",cascade = CascadeType.ALL)
    private List<Funcionario> funcionarioList;

    @OneToMany(mappedBy = "provincia",cascade = CascadeType.ALL)
    private List<Alocacao> alocacaoList;


    @OneToMany(mappedBy = "provincia",cascade = CascadeType.ALL)
    private  List<Distrito> distritoList;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;
}
