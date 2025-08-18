package com.mec.simec_rh.Enums;

import lombok.Getter;

@Getter
public enum TipoSancaoDisciplinar {
    EXPULSAO("Expulsão"),
    DESPROMOCAO("Despromoção"),
    DEMISSAO("Demissão"),
    ADVERTENCIA("Advertência"),
    REPRESSAO_PUBLICA("Repreensão Pública"),
    MULTA("Multa");

    private final String descricao;

    TipoSancaoDisciplinar(String descricao) {
        this.descricao = descricao;
    }

}
