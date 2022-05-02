package com.ot.pessoa.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "telefones")
public class Telefone implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 13)
    @NotBlank
    @Size(min = 13, max = 13)
    private String numero;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne @JoinColumn(name = "pessoa_id")
    private Pessoa dono;

    // contrutores
    public Telefone() {}

    public Telefone(Long id, String numero, String descricao, Pessoa dono) {
        this.id = id;
        this.numero = numero;
        this.descricao = descricao;
        this.dono = dono;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pessoa getDono() {
        return dono;
    }

    public void setDono(Pessoa dono) {
        this.dono = dono;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dono=" + dono +
                '}';
    }
}
