package com.mec.simec_rh.Enums;

import lombok.Getter;

@Getter
public enum TipoCessacao {
    FALECIMENTO("Falecimento"),
    DEMISSAO("Demissão"),
    EXPULSAO("Expulsão"),
    APOSENTACAO("Aposentação"),
    DENUNCIA("Denúncia"),
    RESCISAO("Rescisão"),
    EXONERACAO("Exoneração");

    private final String descricao;

    TipoCessacao(String descricao) {
        this.descricao = descricao;
    }

}
