package com.mec.simec_rh.Enums;

public enum StatusAlocacao {
    ATIVA("Ativa"),
    TRANSFERIDO("Transferido"),
    CESSADO("Cessado"),
    APOSENTADO("Aposentado");

    private final String descricao;

    StatusAlocacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
