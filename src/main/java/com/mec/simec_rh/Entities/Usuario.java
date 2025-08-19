package com.mec.simec_rh.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mec.simec_rh.Enums.Perfil;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    private String username;
    private String email;
    private String password;
    private Perfil perfil;
    private boolean ativo;


    @Timestamp
    private LocalDateTime ultimo_login;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /*Relationship*/

    @OneToOne
    @JoinColumn(name = "funcionario_id")
    @JsonBackReference("funcionario-usuario")
    private  Funcionario funcionario;


    @OneToMany(mappedBy = "alocadoPor", cascade = CascadeType.ALL)
    @JsonManagedReference("usuario-alocacoes")
    private  List<Alocacao> alocacaos;

}
