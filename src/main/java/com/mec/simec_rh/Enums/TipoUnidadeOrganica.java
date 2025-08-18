package com.mec.simec_rh.Enums;

import lombok.Getter;

@Getter
public enum TipoUnidadeOrganica {
    MINISTERIO("Ministério"),
    DIRECCAO_NACIONAL("Direcção Nacional"),
    DIRECCAO_PROVINCIAL("Direcção Provincial"),
    INSTITUTO("Instituto"),
    CONSELHO("Conselho"),
    ESCOLA("Escola");

    private final String descricao;

    TipoUnidadeOrganica(String descricao) {
        this.descricao = descricao;
    }

}
