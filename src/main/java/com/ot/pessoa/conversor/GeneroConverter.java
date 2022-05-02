package com.ot.pessoa.conversor;

import com.ot.pessoa.domain.Genero;
import org.springframework.core.convert.converter.Converter;

public class GeneroConverter implements Converter<String, Genero> {

    @Override
    public Genero convert(String text) {
        char genero = text.charAt(0);
        return genero == Genero.FEMININO.getDesc() ? Genero.FEMININO : Genero.MASCULINO;
    }
}
