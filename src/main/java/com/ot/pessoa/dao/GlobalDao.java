package com.ot.pessoa.dao;

import com.ot.pessoa.domain.Pessoa;
import com.ot.pessoa.domain.Produto;
import com.ot.pessoa.domain.Telefone;

import java.util.List;

public interface GlobalDao {

    void create(Object obj);

    void update(Object obj);

    boolean deletePessoa(Long id);

    Pessoa findByIdPessoa(Long id);

    Telefone findByIdTelefone(Long id);

    Produto findByIdProduto(Long id);

    List<Object> findAllPessoas();

    List<Telefone> findTelefoneByPessoa(Long id, Pessoa pessoa);

    List<Produto> findProdutoByPessoa(Long id, Pessoa pessoa);

    boolean validationPessoa(Pessoa pessoa);

    boolean verificationCPF(Pessoa pessoa);

    boolean validationTelefone(Telefone telefone);

    boolean validationProduto(Produto produto);

    Double totalSpent(Long id, Pessoa pessoa);
}