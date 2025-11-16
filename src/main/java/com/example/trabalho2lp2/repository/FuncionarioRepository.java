package com.example.trabalho2lp2.repository;

import com.example.trabalho2lp2.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Classe FuncionarioRepository estende JpaRepository
 * Busca um funcionário pelo nome
 * Ignorando letras maiúsculas e minúsclas
 */
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByNomeIgnoreCase(String nome);

    /**
     * Busca por um nome de funcinário com mesmo nome
     *
     * @param nome nome do funcionário a ser procurado
     * @return true ou false para a consulta
     */
    boolean existsByNomeIgnoreCase(String nome);
}
