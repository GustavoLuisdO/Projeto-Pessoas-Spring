package com.ot.pessoa.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "telefones")
public class Telefone implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 11)
    @NotBlank @Size(min = 11, max = 11)
    private String numero;

    @Column(nullable = false)
    private boolean celular;

    @ManyToOne @JoinColumn(name = "pessoa_id")
    private Pessoa dono;

    // contrutores
    public Telefone() {}

    public Telefone(Long id, String numero, boolean celular, Pessoa dono) {
        this.id = id;
        this.numero = numero;
        this.celular = celular;
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

    public boolean isCelular() {
        return celular;
    }

    public void setCelular(boolean celular) {
        this.celular = celular;
    }

    public Pessoa getDono() {
        return dono;
    }

    public void setDono(Pessoa dono) {
        this.dono = dono;
    }
}
