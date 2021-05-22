package Detecao;

import Aplicacao.Utilizador;
import Aplicacao.Exceptions.UtilizadorNullException;

import java.util.Random;

public class DetecaoFraudeStub implements DetecaoFraudeInterface {

    private Random rand = new Random();
    int min = 0;    //nao houve fraude
    int max = 1;    //houve fraude
    private int download_ilegal = rand.nextInt((max - min) + 1 + min);

    @Override
    public int detecao_fraude(Utilizador u) throws UtilizadorNullException {
        if (u == null)
            throw new UtilizadorNullException("Utilizador é NULL");
        if (this.download_ilegal == 1) {
            System.out.println("Houve Fraude");
            u.setEstado_utilizador("desativado");
            return download_ilegal;
        }
        return download_ilegal;
    }


}
