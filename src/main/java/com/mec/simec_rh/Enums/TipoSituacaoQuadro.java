package com.mec.simec_rh.Enums;

import lombok.Getter;

@Getter
public enum TipoSituacaoQuadro {
    ACTIVIDADE_FORA_DO_QUADRO("Actividade Fora do Quadro"),
    ACTIVIDADE_NO_QUADRO("Actividade no Quadro"),
    INACTIVIDADE_FORA_DO_QUADRO("Inactividade fora do Quadro"),
    INACTIVIDADE_NO_QUADRO("Inactividade no Quadro"),
    SUPRANUMERARIO("Supranumerario"),
    DESLIGADO("Desligado"),
    SITUACAO_IRREGULAR("Situação irregular"),
    SERVICO_MILITAR("Serviço militar"),
    ESTUDANTE("Estudante"),
    CONTRATADO("Contratado");

    private final String descricao;

    TipoSituacaoQuadro(String descricao) {
        this.descricao = descricao;
    }

}
