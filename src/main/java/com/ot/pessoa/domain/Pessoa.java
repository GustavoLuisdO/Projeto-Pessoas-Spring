package com.ot.pessoa.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoas")
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank @Size(min = 3, max = 100, message = "Campo obrigatório. Deve conter entre {min} e {max} caracteres.")
    private String nome;

    @Column(nullable = false, length = 14)
    @NotBlank @Size(min = 14, max = 14, message = "Campo obrigatório. Deve conter {max} caracteres.")  // [0-9]{3}\.[0-9]{3}\.[0-9]{3}\-[0-9]{2} | 000.000.000-00
    private String cpf;

    @OneToMany(mappedBy = "dono")
    private List<Telefone> telefones = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @OneToMany(mappedBy = "cliente")
    private List<Produto> produtos = new ArrayList<>();

    // contrutores
    public Pessoa() {}

    public Pessoa(Long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
            this.cpf = cpf;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", genero=" + genero +
                '}';
    }
}