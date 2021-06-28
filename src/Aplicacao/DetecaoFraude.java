package Aplicacao;

import Aplicacao.Exceptions.InvalidDetecaoFraudeException;
import Aplicacao.Exceptions.InvalidUserException;

import java.time.LocalDate;

public class DetecaoFraude {

    private int id_detecao;
    private Utilizador utilizador;
    private LocalDate dateDetecao;
    private Funcionario func;

    public DetecaoFraude(int id_detecao, Utilizador utilizador, Funcionario func) throws InvalidDetecaoFraudeException, InvalidUserException {
        if (id_detecao <= 0 || id_detecao >= 30001 )
            throw new InvalidDetecaoFraudeException("Detecao Erro ID");

        if (utilizador == null)
            throw new InvalidDetecaoFraudeException("Detecao Erro User");

        if (func == null)
            throw new InvalidDetecaoFraudeException("Detecao Erro Func");

        if (utilizador.getEstado_utilizador().equals("desativado"))
            throw new InvalidDetecaoFraudeException("Detecao Erro User desativado");

        this.id_detecao = id_detecao;
        this.utilizador = utilizador;
        this.dateDetecao = LocalDate.now();
        this.func = func;

        this.utilizador.setEstado_utilizador("desativado");
    }

    public int getId_detecao() {
        return id_detecao;
    }

    public void setId_detecao(int id_detecao) {
        this.id_detecao = id_detecao;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public LocalDate getDateDetecao() {
        return dateDetecao;
    }

    public void setDateDetecao(LocalDate dateDetecao) {
        this.dateDetecao = dateDetecao;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }
}
