package com.example.trabalho2lp2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

/**Classe Funcionario que representa um entidade do restaurante
 *
 */

@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario {

    /**ID do funcionario gerado automaticamente
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**nome do funcionario não pode ser nulo e precisa ser único
     *
     */
    @NotBlank (message = "O funcionario precisar ter um nome ")
    @Column(nullable = false, unique = true)
    private String nome;
    /**construtor padrão
     *
     */
    public Funcionario() {}
    /**construtor para criar um funcionario
     *
     * @param nome nome do funcionario lembrando que o ID é gerado automaticamente
     */
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

    public void setId( Integer id) {
        this.id = id;
    }
    /**metodo de comparação de dois funcionarios
     *
     * @param o objeto a ser comparado
     * @return true ou false para a consulta
     */
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
