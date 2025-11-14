package com.example.trabalho2lp2.service;

import com.example.trabalho2lp2.model.Funcionario;
import com.example.trabalho2lp2.model.Restaurante;
import com.example.trabalho2lp2.model.Voto;
import com.example.trabalho2lp2.repository.FuncionarioRepository;
import com.example.trabalho2lp2.repository.RestauranteRepository;
import com.example.trabalho2lp2.repository.VotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class VotoService {
    private final VotoRepository votoRepo;
    private final FuncionarioRepository funcRepo;
    private final RestauranteRepository restRepo;

    public VotoService(VotoRepository votoRepo, FuncionarioRepository funcRepo, RestauranteRepository restRepo) {
        this.votoRepo = votoRepo;
        this.funcRepo = funcRepo;
        this.restRepo = restRepo;
    }

    @Transactional
    public void registrarVoto(String nomeFuncionario, String nomeRestaurante, LocalDate data) {
        nomeFuncionario = nomeFuncionario.trim();
        nomeRestaurante = nomeRestaurante.trim();

        // já votou hoje?
        if (votoRepo.existsByFuncionarioNomeIgnoreCaseAndDataVoto(nomeFuncionario, data)) {
            throw new IllegalStateException("Funcionário já votou hoje.");
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

    public List<VotoRepository.VotosPorRestaurante> apurar() {
        return votoRepo.apurarVotosPorRestaurante();
    }
}
