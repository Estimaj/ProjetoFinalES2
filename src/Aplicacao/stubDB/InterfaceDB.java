package Aplicacao.stubDB;

import Aplicacao.*;
import org.json.JSONObject;

public interface InterfaceDB {

    int saveUser(Utilizador u);
    int removeUser(int id_user);
    Utilizador getUser(int id_user);
    Utilizador Login(String email, String pwd);
    String listaOfUsers();

    int saveEBook(EBook eBook);
    EBook getEBook(int idEBook);
    int removeEBook(int idEBook);


    int saveEmprestimo(Emprestimo emp);
    Emprestimo getEmprestimo(int id_emp);
    EBook getEBookFromEmprestimo(int id_emp);
    Utilizador getUtilizadorFromEmprestimo(int id_emp);
    ReplicaServidor getReplicaFromEmprestimo(int id_emp);
    int getAssinaturaTR(int id_emp);
    int removeEmprestimo(int id_emp);



    int saveFuncionario(Funcionario f);
    int removeFuncionario(int id_func);
    Funcionario getFuncionario(int id_func);
    Funcionario LoginFuncionario(String email, String pwd);

    int saveReplica(ReplicaServidor rp);
    int removeReplica(int id_replica);
    ReplicaServidor getReplica(int id_replica);
    String getCopiaFromReplicas(int id_replica);
}
