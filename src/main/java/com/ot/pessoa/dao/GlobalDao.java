package com.ot.pessoa.dao;

import com.ot.pessoa.domain.Pessoa;
import com.ot.pessoa.domain.Telefone;

import java.util.List;

public interface GlobalDao {

    void create(Object obj);

    void update(Object obj);

    void deletePessoa(Long id);

    void deleteTelefone(Long id);

    Pessoa findByIdPessoa(Long id);

    Telefone findByIdTelefone(Long id);

    List<Object> findAllPessoas();

    List<Telefone> findByPessoa(Long id, Pessoa pessoa);
}