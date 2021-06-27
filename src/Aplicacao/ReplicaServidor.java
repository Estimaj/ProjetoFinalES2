package Aplicacao;

import Aplicacao.Exceptions.InvalidReplicaException;

import java.util.ArrayList;

public class ReplicaServidor {
    private int id_replica;
    private String localizacao_Pais_ReplicaServidor;
    private String localizacao_Cidade_ReplicaServidor;
    private ArrayList<CopiaEBook> copiasEBookArrayList = new ArrayList<>();

    public ReplicaServidor(int id_replica, String localizacao_Cidade_ReplicaServidor, String localizacao_Pais_ReplicaServidor) throws InvalidReplicaException {

        if (id_replica <= 0 || id_replica > 30000)
            throw new InvalidReplicaException("Invalid Server Exception");


        if (localizacao_Cidade_ReplicaServidor == null || localizacao_Cidade_ReplicaServidor.chars().allMatch(Character::isDigit) || localizacao_Cidade_ReplicaServidor.equals(""))
            throw new InvalidReplicaException("Invalid Server Exception");

        if (localizacao_Pais_ReplicaServidor == null || localizacao_Pais_ReplicaServidor.chars().allMatch(Character::isDigit) || localizacao_Pais_ReplicaServidor.equals(""))
            throw new InvalidReplicaException("Invalid Server Exception");

        this.id_replica = id_replica;
        this.localizacao_Pais_ReplicaServidor = localizacao_Pais_ReplicaServidor;
        this.localizacao_Cidade_ReplicaServidor = localizacao_Cidade_ReplicaServidor;
    }

    public int getId_replica() {
        return id_replica;
    }

    public void setId_replica(int id_replica) {
        this.id_replica = id_replica;
    }

    public int getSize(){
        return copiasEBookArrayList.size();
    }

    public String getLocalizacao_Cidade_ReplicaServidor() {
        return localizacao_Cidade_ReplicaServidor;
    }

    public void setLocalizacao_Cidade_ReplicaServidor(String localizacao_Cidade_ReplicaServidor) {
        this.localizacao_Cidade_ReplicaServidor = localizacao_Cidade_ReplicaServidor;
    }

    public String getLocalizacao_Pais_ReplicaServidor() {
        return localizacao_Pais_ReplicaServidor;
    }

    public void setLocalizacao_Pais_ReplicaServidor(String localizacao_Pais_ReplicaServidor) {
        this.localizacao_Pais_ReplicaServidor = localizacao_Pais_ReplicaServidor;
    }

    public String getLocalizacaoReplica() {
        return localizacao_Cidade_ReplicaServidor + "," + localizacao_Pais_ReplicaServidor;
    }

    public void addCopiaEBook(CopiaEBook copiaEBook) {
        copiasEBookArrayList.add(copiaEBook);
    }

    public void removeCopiaEBook(CopiaEBook copiaEBook) {
        copiasEBookArrayList.remove(copiaEBook);
    }

    public ArrayList<CopiaEBook> getcopiasEBookArrayList() {
        return copiasEBookArrayList;
    }

    public void setCopiasEBookArrayList(ArrayList<CopiaEBook> copiasEBookArrayList) {
        this.copiasEBookArrayList = copiasEBookArrayList;
    }
}
