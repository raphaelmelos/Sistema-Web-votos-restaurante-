package com.example.trabalho2lp2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/** Classe Voto que une o voto de um funcionario a um restaurante.
 *
 *
 */

@Entity
@Table(name = "VOTO",uniqueConstraints = @UniqueConstraint(columnNames = {"data","id_funcionario"}))
public class Voto {

    /** identificador do voto
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**data do voto
     *
     */
    @NotNull
    @Column(name = "data_voto", nullable = false)
    private LocalDate dataVoto;

    /**funcionario que votou
     *
     */
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    /**restaurante votado
     *
     */
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    /**construtor padrao
     *
     */
    public Voto() {}

    /**construtor para criar o voto
     *
     * @param id id do voto
     * @param dataVoto data do voto
     * @param funcionario funcionario que votou
     * @param restaurante restaurante votado
     */
    public Voto(Integer id, LocalDate dataVoto, Funcionario funcionario, Restaurante restaurante) {
        this.id = id;
        this.dataVoto = dataVoto;
        this.funcionario = funcionario;
        this.restaurante = restaurante;
    }

    /**construtor do voto sem o ID do voto
     *
     * @param funcionario funcionario que votou
     * @param restaurante restaurante votado
     * @param dataVoto data do voto
     */

    public Voto(Funcionario funcionario, Restaurante restaurante, LocalDate dataVoto) {
        this.funcionario = funcionario;
        this.restaurante = restaurante;
        this.dataVoto = dataVoto;
    }

    /**metodos geters e seter para funcionario, restaurante e data do voto
     *
     * @return
     */

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
