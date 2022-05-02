package com.ot.pessoa.domain;

public enum Genero {

    FEMININO('F'),
    MASCULINO('M');

    private char desc;

    Genero(char desc) {
        this.desc = desc;
    }

    public char getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
