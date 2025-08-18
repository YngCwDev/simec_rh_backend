package com.mec.simec_rh.Enums;

import lombok.Getter;

@Getter
public enum TipoNomeacao {
    EFETIVO("Efetivo"),
    INTERINO("Interino"),
    SUBSTITUTO("Substituto"),
    TEMPORARIO("Temporário");

    private final String descricao;

    TipoNomeacao(String descricao) {
        this.descricao = descricao;
    }

}
