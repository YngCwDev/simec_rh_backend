package com.mec.simec_rh.Enums;

public enum Perfil {
    FUNCIONARIO("Funcionário"),
    OPERADOR("Operador"),
    SUPERVISOR("Supervisor"),
    GESTOR("Gestor"),
    ADMINISTRADOR("Administrador");

    private final String descricao;

    Perfil(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
