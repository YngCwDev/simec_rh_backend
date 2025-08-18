package com.mec.simec_rh.Enums;

public enum RegimeEspAct {
    DESTACAMENTO("Destacamento"),
    COMISSAO_SERVICO("Comissão de Serviço"),
    INTERINIDADE("Interinidade"),
    SUBSTITUICAO("Substituição"),
    ACUMULACAO_FUNCOES("Acumulação de Funções");

    private final String descricao;

    RegimeEspAct(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
