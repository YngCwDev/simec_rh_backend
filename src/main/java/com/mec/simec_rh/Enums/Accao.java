package com.mec.simec_rh.Enums;

public enum Accao {
    ENQUADRO("Enquadrado"),
    PROMOVIDO("Promovido"),
    TRANSFERIDO("Transferido"),
    REFORMADO("Reformado"),
    CESSADO("Cessado");

    private final String descricao;

    Accao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

