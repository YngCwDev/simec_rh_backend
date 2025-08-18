package com.mec.simec_rh.Enums;

public enum NivelEducacional {
    PRIMARIO("Primário"),
    BASICO("Básico"),
    MEDIO("Médio"),
    BACHARELATO("Bacharelato"),
    LICENCIATURA("Licenciatura"),
    MESTRADO("Mestrado"),
    DOUTORADO("Doutorado");

    private final String descricao;

    NivelEducacional(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
