package Aplicacao.stubDB;

import Aplicacao.*;
import Aplicacao.Exceptions.InvalidUserException;
import org.json.JSONObject;

public class Database {

    private InterfaceDB interfaceDB = new DBAdapterStub();

    //-----Utilizador
    public synchronized int addUser(Utilizador u) {
        return interfaceDB.saveUser(u);
    }

    public synchronized int removeUser(int id_user) {
        return interfaceDB.removeUser(id_user);
    }

    public synchronized Utilizador getUser(int id_user) {
        return interfaceDB.getUser(id_user);
    }

    public synchronized int updateUser(int id_user, String pwd, String telefone) throws InvalidUserException {
        return interfaceDB.updateUser(id_user,pwd,telefone);
    }

    public synchronized Utilizador Login(String email, String pwd) {
        return interfaceDB.Login(email, pwd);
    }

    public JSONObject ListaOfUsers(){
        return interfaceDB.listaOfUsers();
    }

    //----EBook
    public synchronized int addEBook(EBook eBook) {
        return interfaceDB.saveEBook(eBook);
    }

    public synchronized EBook getEBook(int idEBook) {
        return interfaceDB.getEBook(idEBook);
    }

    public synchronized int removeEBook(int idEBook) {
        return interfaceDB.removeEBook(idEBook);
    }

    //----Emprestimo

    public synchronized int addEmprestimo(Emprestimo emprestimo) {
        return interfaceDB.saveEmprestimo(emprestimo);
    }

    public synchronized Emprestimo getEmprestimo(int id_emp) {
        return interfaceDB.getEmprestimo(id_emp);
    }

    public synchronized EBook getEBookFromEmprestimo(int id_emp) {
        return interfaceDB.getEBookFromEmprestimo(id_emp);
    }

    public synchronized Utilizador getUtilizadorFromEmprestimo(int id_emp) {
        return interfaceDB.getUtilizadorFromEmprestimo(id_emp);
    }

    public synchronized ReplicaServidor getReplicaFromEmprestimo(int id_emp) {
        return interfaceDB.getReplicaFromEmprestimo(id_emp);
    }

    public synchronized int getAssinaturaTR(int id_emp) {
        return interfaceDB.getAssinaturaTR(id_emp);
    }

    public synchronized int removeEmprestimo(int id_emp) {
        return interfaceDB.removeEmprestimo(id_emp);
    }


    //-----Funcionario
    public synchronized int addFuncionario(Funcionario f) {
        return interfaceDB.saveFuncionario(f);
    }

    public synchronized int removeFuncionario(int id_func) {
        return interfaceDB.removeFuncionario(id_func);
    }

    public synchronized Funcionario getFuncionario(int id_func) {
        return interfaceDB.getFuncionario(id_func);
    }

    public synchronized Funcionario LoginFuncionario(String email, String pwd) {
        return interfaceDB.LoginFuncionario(email, pwd);
    }


    //-----Replica
    public synchronized int addReplicaServidor(ReplicaServidor rp) {
        return interfaceDB.saveReplica(rp);
    }

    public synchronized int removeReplicaServidor(int id_replica) {
        return interfaceDB.removeReplica(id_replica);
    }

    public synchronized ReplicaServidor getReplicaServidor(int id_replica) {
        return interfaceDB.getReplica(id_replica);
    }

    public synchronized JSONObject getCopiaFromReplica(int id_replica){
        return interfaceDB.getCopiaFromReplicas(id_replica);
    }

}
