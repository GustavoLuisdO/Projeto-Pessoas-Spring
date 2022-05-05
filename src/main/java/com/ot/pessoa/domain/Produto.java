package com.ot.pessoa.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50) @NotBlank
    @Size(min = 3, max = 50, message = "Campo obrigatório. Deve conter entre {min} e {max} caracteres.")
    private String nomeProduto;

    @Column(nullable = false) @NotBlank
    @Size(min = 1, max = 255, message = "Campo obrigatório. Deve conter entre {min} e {max} caracteres.")
    private String descricao;

    @Column(nullable = false)
    @NotNull(message = "Campo obrigatório.")
    private Double preco;

    @Column(nullable = false)
    @NotNull(message = "Campo obrigatório.")
    private Integer quantidade;

    @ManyToOne @JoinColumn(name = "pessoa_id")
    private Pessoa cliente;

    // construtores
    public Produto() {}

    public Produto(Long id, String nomeProduto, String descricao, Double preco, Integer quantidade, Pessoa cliente) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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
                ", quantidade=" + quantidade +
                ", cliente=" + cliente +
                '}';
    }
}
