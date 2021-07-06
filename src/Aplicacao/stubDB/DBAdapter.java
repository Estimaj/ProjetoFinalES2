package Aplicacao.stubDB;

import Aplicacao.*;
import org.json.JSONObject;

public class DBAdapter implements InterfaceDB {

    private String conexaoDB = "";

    @Override
    public int saveUser(Utilizador u) {
        return -1;
    }

    @Override
    public int removeUser(int id_user) {
        return 0;
    }

    @Override
    public Utilizador getUser(int id_user) {
        return null;
    }

    @Override
    public int updateUser(int id_user, String pwd, String telefone) {
        return 0;
    }

    @Override
    public Utilizador Login(String email, String pwd) {
        return null;
    }

    @Override
    public JSONObject listaOfUsers() {
        return null;
    }

    @Override
    public int saveEBook(EBook eBook) {
        return 0;
    }

    @Override
    public EBook getEBook(int idEBook) {
        return null;
    }

    @Override
    public JSONObject ListarEBooks() {
        return null;
    }

    @Override
    public int removeEBook(int idEBook) {
        return 0;
    }

    @Override
    public int saveEmprestimo(Emprestimo emp) {
        return 0;
    }

    @Override
    public Emprestimo getEmprestimo(int id_emp) {
        return null;
    }

    @Override
    public EBook getEBookFromEmprestimo(int id_emp) {
        return null;
    }

    @Override
    public Utilizador getUtilizadorFromEmprestimo(int id_emp) {
        return null;
    }

    @Override
    public ReplicaServidor getReplicaFromEmprestimo(int id_emp) {
        return null;
    }

    @Override
    public int getAssinaturaTR(int id_emp) {
        return 0;
    }

    @Override
    public int removeEmprestimo(int id_emp) {
        return 0;
    }

    @Override
    public JSONObject ListarEmprestimoByUser(int id_user) {
        return null;
    }

    @Override
    public int saveFuncionario(Funcionario f) {
        return 0;
    }

    @Override
    public int removeFuncionario(int id_func) {
        return 0;
    }

    @Override
    public Funcionario getFuncionario(int id_func) {
        return null;
    }

    @Override
    public Funcionario LoginFuncionario(String email, String pwd) {
        return null;
    }

    @Override
    public int saveReplica(ReplicaServidor rp) {
        return 0;
    }

    @Override
    public int removeReplica(int id_replica) {
        return 0;
    }

    @Override
    public ReplicaServidor getReplica(int id_replica) {
        return null;
    }

    @Override
    public JSONObject getCopiaFromReplicas(int id_replica) {
        return null;
    }

    @Override
    public int addEditora(Editora editora) {
        return 1;
    }

    @Override
    public Editora getEditorabyId(int idEditora) {
        return null;
    }
}
