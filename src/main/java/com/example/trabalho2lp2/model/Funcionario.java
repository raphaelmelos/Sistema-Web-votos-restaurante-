package com.example.trabalho2lp2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    public Funcionario() {}

    public Funcionario(String nome) {
        this.nome = nome;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }

}
