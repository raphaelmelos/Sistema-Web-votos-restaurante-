package com.example.trabalho2lp2.exception;

/**Classe VotoDuplicadoException que estende RuntimeException
 *lança uma exceção para quando o funcionario já votou no mesmo dia
 */
public class VotoDuplicadoExeption extends RuntimeException {
    /**metodo de classe VotoDuplicadoExcepetion
     * @return mensagem para mostrar ao usuário
     */
    public VotoDuplicadoExeption(String message) {
        super(message);

    }
}
