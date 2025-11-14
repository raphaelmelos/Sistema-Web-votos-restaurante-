package com.example.trabalho2lp2.repository;

import com.example.trabalho2lp2.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByNomeIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);
}
