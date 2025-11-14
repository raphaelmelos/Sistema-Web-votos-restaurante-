package com.example.trabalho2lp2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "VOTO",uniqueConstraints = @UniqueConstraint(columnNames = {"data","id_funcionario"}))
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "data_voto", nullable = false)
    private LocalDate dataVoto;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;


    public Voto() {}

    public Voto(Integer id, LocalDate dataVoto, Funcionario funcionario, Restaurante restaurante) {
        this.id = id;
        this.dataVoto = dataVoto;
        this.funcionario = funcionario;
        this.restaurante = restaurante;
    }

    public Voto(Funcionario funcionario, Restaurante restaurante, LocalDate dataVoto) {
        this.funcionario = funcionario;
        this.restaurante = restaurante;
        this.dataVoto = dataVoto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull LocalDate getDataVoto() {
        return dataVoto;
    }

    public void setDataVoto(@NotNull LocalDate dataVoto) {
        this.dataVoto = dataVoto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
