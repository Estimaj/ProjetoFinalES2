import Exceptions.ExtensaoEmprestimoException;
import Exceptions.NotAllowedToReadException;

import java.time.LocalDate;
import java.util.Date;


public class Visualizar {

    public void verificarPodeLer(Emprestimo emprestimo, LocalDate dataLeitura) throws NotAllowedToReadException {

        if(!emprestimo.getUtilizador().getEstado_utilizador().equals("Ativo") || dataLeitura.isAfter(emprestimo.getFimdataHoraEmp()))
        {
            System.out.println("O utilizador não tem permissão para ler este livro!");
            throw new NotAllowedToReadException("O utilizador não tem permissão para ler este livro!");
        }
        //Stub
        System.out.println(emprestimo.getCopiaEBook().getLivro().getTitulo());

    }




}
