package Aplicacao;

import Aplicacao.Exceptions.InvalidDetecaoFraudeException;
import Aplicacao.Exceptions.InvalidUserException;

import java.time.LocalDate;

public class DetecaoFraude1 {

    private int id_detecao;
    private Utilizador utilizador;
    private LocalDate dateDetecao;
    private Funcionario func;

    public DetecaoFraude1(int id_detecao, Utilizador utilizador, Funcionario func) throws InvalidDetecaoFraudeException, InvalidUserException {
        if (id_detecao <= 0 ||utilizador == null || func == null || utilizador.getEstado_utilizador().equals("desativado"))
            throw new InvalidDetecaoFraudeException("Utilizador é NULL");

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
