package apagar.Detecao;

import Aplicacao.Exceptions.InvalidDetecaoFraudeException;
import Aplicacao.Exceptions.InvalidUserException;
import Aplicacao.Utilizador;

import java.util.Random;

public class DetecaoFraudeStub implements DetecaoFraudeInterface {

    private Random rand = new Random();
    private int min = 0;    //nao houve fraude
    private int max = 1;    //houve fraude
    private int download_ilegal = rand.nextInt((max - min) + 1 + min);

    @Override
    public boolean detecao_fraude(Utilizador u) throws InvalidUserException, InvalidDetecaoFraudeException {
        if (u == null)
            throw new InvalidDetecaoFraudeException("Utilizador é NULL");

        if (this.download_ilegal == 1) {
            System.out.println("Houve Fraude");
            u.setEstado_utilizador("desativado");
            return true;
        }
        return false;
    }


}
