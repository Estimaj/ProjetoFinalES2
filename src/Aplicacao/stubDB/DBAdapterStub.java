package Aplicacao.stubDB;

import Aplicacao.*;
import Aplicacao.Exceptions.InvalidUserException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DBAdapterStub implements InterfaceDB {

    private ArrayList<Emprestimo> emprestimoArrayList = new ArrayList<>();
    private ArrayList<EBook> ebookArrayList = new ArrayList<>();
    private ArrayList<Utilizador> utilizadorArrayList = new ArrayList<>();
    private ArrayList<ReplicaServidor> replicaServidorArrayList = new ArrayList<>();
    private ArrayList<Funcionario> funcionarioArrayList = new ArrayList<>();

    @Override
    public int saveUser(Utilizador u) {
        if (u != null) {
            utilizadorArrayList.add(u);
            return u.getId_utilizador();
        }

        return -1;
    }

    @Override
    public int removeUser(int id_user) {
        for (int i = 0; i < utilizadorArrayList.size(); i++) {
            if (utilizadorArrayList.get(i).getId_utilizador().equals(id_user)) {
                utilizadorArrayList.remove(i);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public Utilizador getUser(int id_user) {
        for (int i = 0; i < utilizadorArrayList.size(); i++) {
            if (utilizadorArrayList.get(i).getId_utilizador().equals(id_user))
                return utilizadorArrayList.get(i);
        }
        return null;
    }

    @Override
    public int updateUser(int id_user, String pwd, String telefone) throws InvalidUserException {
        if (pwd == null || pwd == "" || telefone == null || telefone == ""){
            return 0;
        }
        for (int i = 0; i < utilizadorArrayList.size(); i++) {
            if (utilizadorArrayList.get(i).getId_utilizador().equals(id_user)){
                utilizadorArrayList.get(i).setPwd_utilizador(pwd);
                utilizadorArrayList.get(i).setTelefone_utilizador(telefone);
                return utilizadorArrayList.get(i).getId_utilizador();
            }
        }
        return 0;
    }

    @Override
    public Utilizador Login(String email, String pwd) {
        if (email == null || pwd == null)
            return null;

        for (int i = 0; i < utilizadorArrayList.size(); i++) {
            if (utilizadorArrayList.get(i).getEmail_utilizador().equals(email) && utilizadorArrayList.get(i).getPwd_utilizador().equals(pwd))
                return utilizadorArrayList.get(i);
        }
        return null;
    }

    @Override
    public JSONObject listaOfUsers() {
        String message;
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();
        int key = 0;
        String nome = null, email = null, morada = null, contacto = null, estado = null;
        for (int i = 0; i < utilizadorArrayList.size(); i++) {
            key = utilizadorArrayList.get(i).getId_utilizador();
            nome = utilizadorArrayList.get(i).getNome_utilizador();
            email = utilizadorArrayList.get(i).getEmail_utilizador();
            morada = utilizadorArrayList.get(i).getMorada_utilizador();
            contacto = utilizadorArrayList.get(i).getTelefone_utilizador();
            estado = utilizadorArrayList.get(i).getEstado_utilizador();
            item.put("Nome",nome);
            item.put("Email",email);
            item.put("Morada",morada);
            item.put("Contacto",contacto);
            item.put("Estado",estado);
            array.put(item);
            json.put(String.valueOf(key), array);
            array = new JSONArray();
        }
        message = json.toString();
        System.out.println(message);

        return json;
    }

    @Override
    public int saveEBook(EBook eBook) {
        if (eBook != null) {
            ebookArrayList.add(eBook);
            return eBook.getIdEbook();
        }

        return -1;
    }

    @Override
    public EBook getEBook(int idEBook) {
        for (int i = 0; i < ebookArrayList.size(); i++) {
            if (ebookArrayList.get(i).getIdEbook() == idEBook)
                return ebookArrayList.get(i);
        }
        return null;
    }

    @Override
    public int removeEBook(int idEBook) {
        for (int i = 0; i < ebookArrayList.size(); i++) {
            if (ebookArrayList.get(i).getIdEbook() == idEBook) {
                ebookArrayList.remove(i);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int saveEmprestimo(Emprestimo emp) {
        if (emp != null) {
            emprestimoArrayList.add(emp);
            return emp.getId_emp();
        }
        return -1;
    }

    @Override
    public Emprestimo getEmprestimo(int id_emp) {
        for (int i = 0; i < emprestimoArrayList.size(); i++) {
            if (emprestimoArrayList.get(i).getId_emp() == id_emp) {
                return emprestimoArrayList.get(i);
            }
        }
        return null;
    }

    @Override
    public EBook getEBookFromEmprestimo(int id_emp) {
        for (int i = 0; i < emprestimoArrayList.size(); i++) {
            if (emprestimoArrayList.get(i).getId_emp() == id_emp) {
                return emprestimoArrayList.get(i).getEBook();
            }
        }
        return null;
    }

    @Override
    public Utilizador getUtilizadorFromEmprestimo(int id_emp) {
        for (int i = 0; i < emprestimoArrayList.size(); i++) {
            if (emprestimoArrayList.get(i).getId_emp() == id_emp) {
                return emprestimoArrayList.get(i).getUtilizador();
            }
        }
        return null;
    }

    @Override
    public ReplicaServidor getReplicaFromEmprestimo(int id_emp) {
        for (int i = 0; i < emprestimoArrayList.size(); i++) {
            if (emprestimoArrayList.get(i).getId_emp() == id_emp) {
                return emprestimoArrayList.get(i).getReplicaServidor();
            }
        }
        return null;
    }

    @Override
    public int getAssinaturaTR(int id_emp) {
        for (int i = 0; i < emprestimoArrayList.size(); i++) {
            if (emprestimoArrayList.get(i).getId_emp() == id_emp) {
                return emprestimoArrayList.get(i).getAssinaturaTR();
            }
        }
        return -1;
    }

    @Override
    public int removeEmprestimo(int id_emp) {
        for (int i = 0; i < emprestimoArrayList.size(); i++) {
            if (emprestimoArrayList.get(i).getId_emp() == id_emp) {
                emprestimoArrayList.remove(i);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int saveFuncionario(Funcionario f) {
        if (f != null) {
            funcionarioArrayList.add(f);
            return f.getId_func();
        }

        return -1;
    }

    @Override
    public int removeFuncionario(int id_func) {
        for (int i = 0; i < funcionarioArrayList.size(); i++) {
            if (funcionarioArrayList.get(i).getId_func().equals(id_func)) {
                funcionarioArrayList.remove(i);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public Funcionario getFuncionario(int id_func) {
        for (int i = 0; i < funcionarioArrayList.size(); i++) {
            if (funcionarioArrayList.get(i).getId_func().equals(id_func))
                return funcionarioArrayList.get(i);
        }
        return null;
    }

    @Override
    public Funcionario LoginFuncionario(String email, String pwd) {
        if (email == null || pwd == null)
            return null;

        for (int i = 0; i < funcionarioArrayList.size(); i++) {
            if (funcionarioArrayList.get(i).getEmail_func().equals(email) && funcionarioArrayList.get(i).getPwd_func().equals(pwd))
                return funcionarioArrayList.get(i);
        }
        return null;
    }

    @Override
    public int saveReplica(ReplicaServidor rp) {
        if (rp != null) {
            replicaServidorArrayList.add(rp);
            return rp.getId_replica();
        }
        return -1;
    }

    @Override
    public int removeReplica(int id_replica) {
        for (int i = 0; i < replicaServidorArrayList.size(); i++) {
            if (replicaServidorArrayList.get(i).getId_replica() == id_replica) {
                replicaServidorArrayList.remove(i);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public ReplicaServidor getReplica(int id_replica) {
        for (int i = 0; i < replicaServidorArrayList.size(); i++) {
            if (replicaServidorArrayList.get(i).getId_replica() == id_replica) {
                return replicaServidorArrayList.get(i);
            }
        }
        return null;
    }

    @Override
    public JSONObject getCopiaFromReplicas(int id_replica) {
        String message;
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();
        int key = 0;
        String titulo = null, autor = null, editora = null, formato = null;
        float filesize;
        StringBuilder s = new StringBuilder();
        for (ReplicaServidor replicaServidor : replicaServidorArrayList) {
            if (replicaServidor.getId_replica() == id_replica) {
                for (int j = 0; j < replicaServidor.getcopiasEBookArrayList().size(); j++) {
                    key = replicaServidor.getcopiasEBookArrayList().get(j).getId();
                    titulo = replicaServidor.getcopiasEBookArrayList().get(j).getEBook().getTitulo();
                    autor = replicaServidor.getcopiasEBookArrayList().get(j).getEBook().getAutor();
                    editora = replicaServidor.getcopiasEBookArrayList().get(j).getEBook().getEditora();
                    formato = replicaServidor.getcopiasEBookArrayList().get(j).getEBook().getFormato();
                    filesize = replicaServidor.getcopiasEBookArrayList().get(j).getEBook().getFileSize();
                    item.put("Titulo",titulo);
                    item.put("Autor",autor);
                    item.put("Editora",editora);
                    item.put("Formato",formato);
                    item.put("FileSize",filesize);
                    array.put(item);
                    json.put(String.valueOf(key), array);
                    array = new JSONArray();
                }
            }
        }
        message = json.toString();
        System.out.println(message);
        return json;
    }


}
