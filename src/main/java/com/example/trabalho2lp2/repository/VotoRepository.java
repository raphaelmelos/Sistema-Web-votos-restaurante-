package com.example.trabalho2lp2.repository;

import com.example.trabalho2lp2.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
/**
Classe VotoRepository extends jpaRepository
 */
public interface VotoRepository extends JpaRepository<Voto, Integer> {
    boolean existsByFuncionarioNomeIgnoreCaseAndDataVoto(String nome, LocalDate dataVoto);

    /** metodo que imprimi os votos de um restaurante de forma estruturada
     */
    interface VotosPorRestaurante {
        String getNome();
        Long getVotos();
    }
    /**
    Query para mostrar restaurantes agrupados por nome e votos de forma decrescente
     */

    @Query("SELECT r.nome as nome, COUNT(v) as votos " +
            "FROM Voto v JOIN v.restaurante r " +
            "GROUP BY r.nome " +
            "ORDER BY votos DESC, r.nome ASC")
    List<VotosPorRestaurante> apurarVotosPorRestaurante();
}
