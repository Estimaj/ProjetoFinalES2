package Aplicacao.stubDB;

import Aplicacao.*;
import org.json.JSONObject;

import java.util.ArrayList;

public class Database {

    private InterfaceDB interfaceDB = new DBAdapterStub();

    //-----Utilizador
    public int addUser(Utilizador u) {
        return interfaceDB.saveUser(u);
    }

    public int removeUser(int id_user) {
        return interfaceDB.removeUser(id_user);
    }

    public Utilizador getUser(int id_user) {
        return interfaceDB.getUser(id_user);
    }

    public Utilizador Login(String email, String pwd) {
        return interfaceDB.Login(email, pwd);
    }


    //----EBook
    public int addEBook(EBook eBook) {
        return interfaceDB.saveEBook(eBook);
    }

    public EBook getEBook(int idEBook) {
        return interfaceDB.getEBook(idEBook);
    }

    public int removeEBook(int idEBook) {
        return interfaceDB.removeEBook(idEBook);
    }

    //----Emprestimo

    public int addEmprestimo(Emprestimo emprestimo) {
        return interfaceDB.saveEmprestimo(emprestimo);
    }

    public Emprestimo getEmprestimo(int id_emp) {
        return interfaceDB.getEmprestimo(id_emp);
    }

    public EBook getEBookFromEmprestimo(int id_emp) {
        return interfaceDB.getEBookFromEmprestimo(id_emp);
    }

    public Utilizador getUtilizadorFromEmprestimo(int id_emp) {
        return interfaceDB.getUtilizadorFromEmprestimo(id_emp);
    }

    public ReplicaServidor getReplicaFromEmprestimo(int id_emp) {
        return interfaceDB.getReplicaFromEmprestimo(id_emp);
    }

    public int getAssinaturaTR(int id_emp) {
        return interfaceDB.getAssinaturaTR(id_emp);
    }

    public int removeEmprestimo(int id_emp) {
        return interfaceDB.removeEmprestimo(id_emp);
    }


    //-----Funcionario
    public int addFuncionario(Funcionario f) {
        return interfaceDB.saveFuncionario(f);
    }

    public int removeFuncionario(int id_func) {
        return interfaceDB.removeFuncionario(id_func);
    }

    public Funcionario getFuncionario(int id_func) {
        return interfaceDB.getFuncionario(id_func);
    }

    public Funcionario LoginFuncionario(String email, String pwd) {
        return interfaceDB.LoginFuncionario(email, pwd);
    }


    //-----Replica
    public int addReplicaServidor(ReplicaServidor rp) {
        return interfaceDB.saveReplica(rp);
    }

    public int removeReplicaServidor(int id_replica) {
        return interfaceDB.removeReplica(id_replica);
    }

    public ReplicaServidor getReplicaServidor(int id_replica) {
        return interfaceDB.getReplica(id_replica);
    }

    public JSONObject getCopiaFromReplica(int id_replica){
        return interfaceDB.getCopiaFromReplicas(id_replica);
    }

}
