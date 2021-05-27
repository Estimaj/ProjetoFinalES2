package apagar.Detecao;

import Aplicacao.Exceptions.InvalidDetecaoFraudeException;
import Aplicacao.Exceptions.InvalidUserException;
import Aplicacao.Utilizador;
import Aplicacao.Exceptions.UtilizadorNullException;

public interface DetecaoFraudeInterface {
    boolean detecao_fraude(Utilizador u) throws UtilizadorNullException, InvalidUserException, InvalidDetecaoFraudeException;
}
