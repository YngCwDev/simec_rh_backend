package com.mec.simec_rh.Enums;

public enum CategoriaFuncao {
    DIRECAO("Direção"),
    CHEFIA("Chefia"),
    TECNICA("Técnica"),
    ADMINISTRATIVA("Administrativa"),
    DOCENTE("Docente");

    private final String descricao;

    CategoriaFuncao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
