package com.example.trabalho2lp2.service;

import com.example.trabalho2lp2.exception.VotoDuplicadoExeption;
import com.example.trabalho2lp2.model.Funcionario;
import com.example.trabalho2lp2.model.Restaurante;
import com.example.trabalho2lp2.model.Voto;
import com.example.trabalho2lp2.repository.FuncionarioRepository;
import com.example.trabalho2lp2.repository.RestauranteRepository;
import com.example.trabalho2lp2.repository.VotoRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Essa classe gerencia a lóica de votação
 * Aqui temos os metodos de votação, definindo que o o funcionário só pode votar uma vez
 * Criação automatica de funcionários não cadastrados
 */

@Service
public class VotoService {
    private final VotoRepository votoRepo;
    private final FuncionarioRepository funcRepo;
    private final RestauranteRepository restRepo;

    /**
     * Construtor de VotosServive
     *
     * @param votoRepo
     * @param funcRepo
     * @param restRepo
     */

    public VotoService(VotoRepository votoRepo, FuncionarioRepository funcRepo, RestauranteRepository restRepo) {
        this.votoRepo = votoRepo;
        this.funcRepo = funcRepo;
        this.restRepo = restRepo;
    }

    /**
     * Metodo que regitra um voto em um restaurante.
     *
     * @param nomeFuncionario funcionário recebido para o voto
     * @param nomeRestaurante restaurante recebido para o voto
     * @param data            data do voto
     */

    @Transactional
    public void registrarVoto(String nomeFuncionario, String nomeRestaurante, LocalDate data) {
        nomeFuncionario = nomeFuncionario.trim();
        nomeRestaurante = nomeRestaurante.trim();


        if (votoRepo.existsByFuncionarioNomeIgnoreCaseAndDataVoto(nomeFuncionario, data)) {
            throw new VotoDuplicadoExeption("Voto duplicado");
        }


        String nomeFuncionarioTrim = nomeFuncionario.trim();
        String nomeRestauranteTrim = nomeRestaurante.trim();

        Funcionario funcionario = funcRepo.findByNomeIgnoreCase(nomeFuncionarioTrim)
                .orElseGet(() -> funcRepo.save(new Funcionario(nomeFuncionarioTrim)));

        Restaurante restaurante = restRepo.findByNomeIgnoreCase(nomeRestauranteTrim)
                .orElseGet(() -> restRepo.save(new Restaurante(nomeRestauranteTrim)));


        Voto voto = new Voto(funcionario, restaurante, data);
        votoRepo.save(voto);
    }

    /**
     * Apuração de votos
     *
     * @return lista com quantidadde de votos de cada restaurante
     */

    public List<VotoRepository.VotosPorRestaurante> apurar() {
        return votoRepo.apurarVotosPorRestaurante();
    }
}
