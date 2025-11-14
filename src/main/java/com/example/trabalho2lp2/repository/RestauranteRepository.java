package com.example.trabalho2lp2.repository;

import com.example.trabalho2lp2.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {
    Optional<Restaurante> findByNomeIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);
}
