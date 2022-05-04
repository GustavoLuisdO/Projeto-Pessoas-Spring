package com.ot.pessoa.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50) @NotBlank
    private String nomeProduto;

    @Column(nullable = false) @NotBlank
    private String descricao;

    @Column(nullable = false)
    private Double preco;

    @ManyToOne @JoinColumn(name = "pessoa_id")
    private Pessoa cliente;

    // construtores
    public Produto() {}

    public Produto(Long id, String nomeProduto, String descricao, Double preco, Pessoa cliente) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.preco = preco;
        this.cliente = cliente;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", cliente=" + cliente +
                '}';
    }
}
