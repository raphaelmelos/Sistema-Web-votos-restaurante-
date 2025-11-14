package com.example.trabalho2lp2.controller;

import com.example.trabalho2lp2.repository.VotoRepository;
import com.example.trabalho2lp2.service.VotoService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping ("/")
public class VotoController {


    @Autowired
    private VotoService votoService;
    @Autowired
    private VotoRepository votoRepository;

   @GetMapping("votos")
    public String home() {
        return "home";
    }

    @GetMapping("votos/cadastrar")
    public String formCadastro(Model model) {
        model.addAttribute("form", new VotoForm());
        return "cadastrar";
    }

    @PostMapping("/votos/cadastrar")
    public String cadastrar(@ModelAttribute("form") VotoForm form, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "cadastrar";
        }
        try {
            votoService.registrarVoto(form.getFuncionario(), form.getRestaurante(), LocalDate.now());
            model.addAttribute("mensagemSucesso", "Voto registrado com sucesso!");
        } catch (IllegalStateException ex) {
            model.addAttribute("erro", ex.getMessage());
        }
        return "cadastrar";
    }

    @GetMapping("/votos/apurar")
    public String apurar(Model model) {
        List<VotoRepository.VotosPorRestaurante> lista = votoService.apurar();
        model.addAttribute("apuracao", lista);
        return "apurar";
    }

    public static class VotoForm {
        @NotBlank
        private String funcionario;
        @NotBlank
        private String restaurante;

        public String getFuncionario(){return funcionario;}
        public void setFuncionario(String funcionario){this.funcionario = funcionario;}
        public String getRestaurante(){return restaurante;}
        public void setRestaurante(String restaurante){this.restaurante = restaurante;}
    }
}
