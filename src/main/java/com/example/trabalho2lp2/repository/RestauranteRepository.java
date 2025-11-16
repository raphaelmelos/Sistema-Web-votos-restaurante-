package com.example.trabalho2lp2.repository;

import com.example.trabalho2lp2.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Classe RestauranteRepository extends jpaRepository
 * Busca um Restaurante pelo nome.
 * Ignora letras maiúculas e minúsculas
 */

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {
    Optional<Restaurante> findByNomeIgnoreCase(String nome);

    /**
     * Busca por restaurante com o mesmo nome
     *
     * @param nome nome do restaurante a ser procurado
     * @return true ou false para a consulta
     */
    boolean existsByNomeIgnoreCase(String nome);
}
