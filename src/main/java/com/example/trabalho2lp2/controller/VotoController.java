package com.example.trabalho2lp2.controller;

import com.example.trabalho2lp2.exception.VotoDuplicadoExeption;
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
/**Classe responsável por gerenciar as operações relacionadas aos votos
 * incluindo exibição da página inicial, cadastro de votos e apuração da votação.
 *
 */
@Controller
@RequestMapping ("/")
public class VotoController {


    @Autowired
    private VotoService votoService;
    @Autowired
    private VotoRepository votoRepository;

    /**Redireciona para a pagina inicial do sistema de votação
     *
     * @return nome da view "home.html"
     */
   @GetMapping("votos")
    public String home() {
        return "home";
    }

    /**Exibe  o formulario  para cadastrar um voto
     *
     * @param model modelo usado para enviar atributos a view
     * @return nome da view "cadastrar.html"
     */
    @GetMapping("votos/cadastrar")
    public String formCadastro(Model model) {
        model.addAttribute("form", new VotoForm());
        return "cadastrar";
    }

    /**processa o cadastro de um voto enviado pelo formulário
     *
     * @param form objeto contendo os dados do voto
     * @param br objeto para verificação de erros de validação
     * @param model Modelo usado para enviar mensagens à view
     * @return permanece na view de cadastro com mensagens de sucesso ou erro
     */
    @PostMapping("votos/cadastrar")
    public String cadastrar(@ModelAttribute("form") VotoForm form, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "cadastrar";
        }
        try {
            votoService.registrarVoto(form.getFuncionario(), form.getRestaurante(), LocalDate.now());
            model.addAttribute("mensagemSucesso", "Voto registrado com sucesso!");
        } catch (VotoDuplicadoExeption exeption) {
            model.addAttribute("erro", exeption.getMessage());
            return "erro";
        }
        return "cadastrar";
    }

    /**mostra o resultado da apuração de votos agrupodos por restaurante
     *
     * @param model modelo usado para enviar a lista apurada a view
     * @return nome da view "apurar.html"
     */
    @GetMapping("/votos/apurar")
    public String apurar(Model model) {
        List<VotoRepository.VotosPorRestaurante> lista = votoService.apurar();
        model.addAttribute("apuracao", lista);
        return "apurar";
    }

    /**Classe interna utilizada para receber os dados do formulário de cadastro de votos
     */
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
