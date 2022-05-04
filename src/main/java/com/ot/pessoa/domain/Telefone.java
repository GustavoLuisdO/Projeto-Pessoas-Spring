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
    private String numero; // [0-9]{2}\ [0-9]{5}\-[0-9]{4} | 11 90000-0000

    @Column(nullable = false)
    private String obs;

    @ManyToOne @JoinColumn(name = "pessoa_id")
    private Pessoa dono;

    // contrutores
    public Telefone() {}

    public Telefone(Long id, String numero, String obs, Pessoa dono) {
        setId(null);
        this.numero = numero;
        this.obs = obs;
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

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
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
                ", obs='" + obs + '\'' +
                ", dono=" + dono +
                '}';
    }
}
