package Aplicacao;

import Aplicacao.Exceptions.NotAllowedToReadException;

import java.time.LocalDate;


public class Visualizar {

    private Emprestimo emp;

    public boolean verificarPodeLer(Emprestimo emprestimo, LocalDate dataLeitura) throws NotAllowedToReadException {
        emp = emprestimo;
        if(!emprestimo.getUtilizador().getEstado_utilizador().equals("ativo") || dataLeitura.isBefore(emprestimo.getDataHoraEmp()) || dataLeitura.isAfter(emprestimo.getFimdataHoraEmp())) {
            System.out.println("O utilizador não tem permissão para ler este livro!");
            throw new NotAllowedToReadException("O utilizador não tem permissão para ler este livro!");
        }
        System.out.println("Pode Ler o Livro!");
        return true;


    }
}