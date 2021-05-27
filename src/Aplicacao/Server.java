package Aplicacao;

import Aplicacao.Exceptions.InvalidServerException;

import java.util.ArrayList;

public class Server {
    private String localizacao_Server;
    private ArrayList<ReplicaServidor> replicasArrayList = new ArrayList<>();

    public Server(String localizacao_Server) throws InvalidServerException {
        if (localizacao_Server.chars().allMatch(Character::isDigit) || localizacao_Server == null || localizacao_Server == "")
            throw new InvalidServerException("Invalid Server Exception");

        this.localizacao_Server = localizacao_Server.toLowerCase();
    }

    public String getLocalizacao_Server() {
        return localizacao_Server;
    }

    public void setLocalizacao_Server(String localizacao_Server) {
        this.localizacao_Server = localizacao_Server;
    }

    public void addReplica(ReplicaServidor replicasArrayListervidor) {
        replicasArrayList.add(replicasArrayListervidor);
    }

    public void removeReplica(ReplicaServidor replicasArrayListervidor) {
        replicasArrayList.add(replicasArrayListervidor);
    }

    public CopiaEBook getCopia_of_Replica(CopiaEBook copiaEBook) {
        for (ReplicaServidor replicaServidor : replicasArrayList) {
            if (replicaServidor.getCopiaEBook().equals(copiaEBook))
                return replicaServidor.getCopiaEBook();
        }
        return null;
    }

    public CopiaEBook getCopia_of_Replica1(ReplicaServidor rp) {
        for (ReplicaServidor replicaServidor : replicasArrayList) {
            if (replicaServidor.equals(rp))
                return rp.getCopiaEBook();
        }
        return null;
    }

    public ReplicaServidor getReplicabyElement(ReplicaServidor replicaServidor) {
        for (ReplicaServidor servidor : replicasArrayList) {
            if (servidor.equals(replicaServidor))
                return servidor;
        }
        return null;
    }

    public ReplicaServidor get_ReplicaServidor_by_id(int index) {
        return this.replicasArrayList.get(index);
    }

    public String show_info_replicas() {
        String to_return = null;
        for (ReplicaServidor replicaServidor : replicasArrayList) {
            to_return = replicaServidor.getLocalizacao_Cidade_ReplicaServidor() + ", " + replicaServidor.getCopiaEBook().getEBook().getTitulo();
        }
        System.out.println(to_return);
        return to_return;
    }

    public ArrayList<ReplicaServidor> getreplicasArrayList() {
        return replicasArrayList;
    }

    public void setreplicasArrayList(ArrayList<ReplicaServidor> replicasArrayList) {
        this.replicasArrayList = replicasArrayList;
    }

    public int get_replicas_ArrayList_Size() {
        return this.replicasArrayList.size();
    }

    public ReplicaServidor get_Replica_Proxima_Cliente(Utilizador u) {
        String[] parts = u.getMorada_utilizador().split(",");
        String cidade = parts[0]; // get cidade
        String pais = parts[1].replaceAll("\\s+", ""); // remove spaces
        String[] partsReplica;
        String cidadeReplica, paisReplica;

        if (replicasArrayList.size() == 0)
            return null;
        if (replicasArrayList.size() == 1)
            return replicasArrayList.get(0);

        for (int i = 0; i < replicasArrayList.size(); i++) {

            partsReplica = replicasArrayList.get(i).getLocalizacao_Cidade_ReplicaServidor().split(",");
            cidadeReplica = partsReplica[0];
            paisReplica = partsReplica[1].replaceAll("\\s+", "");

            if (cidadeReplica.equals(cidade) && paisReplica.equals(pais)) {
                System.out.println("cidade = " + cidade + " | pais = " + pais);
                return replicasArrayList.get(i);
            }

        }
        if (replicasArrayList.size() > 2){
            System.out.println("size > 2");
            return replicasArrayList.get(replicasArrayList.size() - 1);
        }

        return null;
    }

}
