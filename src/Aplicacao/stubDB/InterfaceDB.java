package Aplicacao.stubDB;

import Aplicacao.*;
import Aplicacao.Exceptions.InvalidUserException;
import org.json.JSONObject;

public interface InterfaceDB {

    int saveUser(Utilizador u);
    int removeUser(int id_user);
    Utilizador getUser(int id_user);
    int updateUser(int id_user, String pwd, String telefone) throws InvalidUserException;
    Utilizador Login(String email, String pwd);
    JSONObject listaOfUsers();

    int saveEBook(EBook eBook);
    EBook getEBook(int idEBook);
    JSONObject ListarEBooks();
    int removeEBook(int idEBook);


    int saveEmprestimo(Emprestimo emp);
    Emprestimo getEmprestimo(int id_emp);
    EBook getEBookFromEmprestimo(int id_emp);
    Utilizador getUtilizadorFromEmprestimo(int id_emp);
    ReplicaServidor getReplicaFromEmprestimo(int id_emp);
    int getAssinaturaTR(int id_emp);
    int removeEmprestimo(int id_emp);
    JSONObject ListarEmprestimoByUser(int id_user);



    int saveFuncionario(Funcionario f);
    int removeFuncionario(int id_func);
    Funcionario getFuncionario(int id_func);
    Funcionario LoginFuncionario(String email, String pwd);

    int saveReplica(ReplicaServidor rp);
    int removeReplica(int id_replica);
    ReplicaServidor getReplica(int id_replica);
    JSONObject getCopiaFromReplicas(int id_replica);

    int addEditora(Editora editora);
    Editora getEditorabyId(int idEditora);
}
