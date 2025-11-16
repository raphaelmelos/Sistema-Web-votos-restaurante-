package com.example.trabalho2lp2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

/**Classe Restaurante que representa um entidade do restaurante
 *
 *
 */

@Entity
@Table(name = "RESTAURANTE")
public class Restaurante {

    /**ID do restaurante gerado automaticamente
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**nome do restaurante não pode ser nulo e precisa ser único
     *
     */
    @NotBlank (message = "Restaurante precisa ter um nome")
    @Column(nullable = false, unique = true)
    private String nome;

    /**construtor padrão
     *
     */
    public Restaurante() {}

    /**construtor para criar um restaurante
     *
     * @param nome nome do restaurante lembrando que o ID é gerado automaticamente
     */
    public Restaurante(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    /**metodo de comparação de dois restaurantes
     *
     * @param o objeto a ser comparado
     * @return true ou false para a consulta
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return Objects.equals(id, that.id);
    }

    /**gera um hash baseado no restaurante
     *
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
