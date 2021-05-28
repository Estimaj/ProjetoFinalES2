package Aplicacao;

import Aplicacao.Exceptions.InvalidReplicaException;

import java.util.ArrayList;

public class ReplicaServidor {
    private String localizacao_Pais_ReplicaServidor;
    private String localizacao_Cidade_ReplicaServidor;
    private ArrayList<CopiaEBook> copiasEBookArrayList = new ArrayList<>();

    public ReplicaServidor(String localizacao_Cidade_ReplicaServidor, String localizacao_Pais_ReplicaServidor) throws InvalidReplicaException {
        if (localizacao_Cidade_ReplicaServidor == null || localizacao_Cidade_ReplicaServidor.chars().allMatch(Character::isDigit) || localizacao_Cidade_ReplicaServidor.equals(""))
            throw new InvalidReplicaException("Invalid Server Exception");

        if (localizacao_Pais_ReplicaServidor == null || localizacao_Pais_ReplicaServidor.chars().allMatch(Character::isDigit) || localizacao_Pais_ReplicaServidor.equals(""))
            throw new InvalidReplicaException("Invalid Server Exception");

        this.localizacao_Pais_ReplicaServidor = localizacao_Pais_ReplicaServidor;
        this.localizacao_Cidade_ReplicaServidor = localizacao_Cidade_ReplicaServidor;
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
