package com.mec.simec_rh.Enums;

public enum Licenca {
    ACOMPANHAMENTO_CONJUUGE("Acompanhamento de Cônjuge"),
    LICENCA_DISCIPLINAR("Licença Disciplinar"),
    LICENCA_ESPECIAL("Licença Especial"),
    LICENCA_ILIMITADA("Licença Ilimitada"),
    LICENCA_POR_DOENCA("Licença por Doença"),
    LICENCA_3_MESES("Licença registada 3 MESES"),
    LICENCA_6_MESES("Licença registada 6 MESES"),
    LICENCA_12_MESES("Licença registada 12 MESES"),
    LICENCA_012("012"); // Caso especial, mantido como você digitou

    private final String descricao;

    Licenca(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
