package com.ot.pessoa.dao;

import com.ot.pessoa.domain.Pessoa;
import com.ot.pessoa.domain.Telefone;

import java.util.List;

public interface GlobalDao {

    void create(Object obj);

    void update(Object obj);

    boolean deletePessoa(Long id);

    Pessoa findByIdPessoa(Long id);

    Telefone findByIdTelefone(Long id);

    List<Object> findAllPessoas();

    List<Telefone> findByPessoa(Long id, Pessoa pessoa);

    boolean validationPessoa(Pessoa pessoa);

    boolean validationTelefone(Telefone telefone);
}