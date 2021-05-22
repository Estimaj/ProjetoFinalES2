package Detecao;

import Aplicacao.Utilizador;
import Aplicacao.Exceptions.UtilizadorNullException;

public interface DetecaoFraudeInterface {
    int detecao_fraude(Utilizador u) throws UtilizadorNullException;
}
