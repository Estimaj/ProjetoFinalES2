package Aplicacao;

import Aplicacao.Exceptions.InvalidReplicaException;

import java.util.ArrayList;

public class ReplicaServidor {

    private int id_replica;
    private String localizacao_Pais_ReplicaServidor;
    private ArrayList<CopiaEBook> copiasEBookArrayList = new ArrayList<>();
    private String ipReplica;
    private static final String zeroTo255 = "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])";
    private static final String IP_VERIFICATION = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255;

    public ReplicaServidor(int id_replica, String localizacao_Pais_ReplicaServidor, String ipReplica) throws InvalidReplicaException {
        if (id_replica <= 0 || id_replica >= 30001)
            throw new InvalidReplicaException("Invalid Server Exception");

        if (localizacao_Pais_ReplicaServidor == null || localizacao_Pais_ReplicaServidor.chars().allMatch(Character::isDigit) || localizacao_Pais_ReplicaServidor.equals(""))
            throw new InvalidReplicaException("Invalid Server Exception");

        if (!ipReplica.matches(IP_VERIFICATION) || ipReplica.equals(""))
            throw new InvalidReplicaException("Invalid Server Exception IP");

        this.id_replica = id_replica;
        this.ipReplica = ipReplica;
        this.localizacao_Pais_ReplicaServidor = localizacao_Pais_ReplicaServidor;
    }

    public int getId_replica() {
        return id_replica;
    }

    public void setId_replica(int id_replica) throws InvalidReplicaException {
        if (id_replica <= 0 || id_replica >= 30001)
            throw new InvalidReplicaException("Invalid Server Exception");

        this.id_replica = id_replica;
    }

    public String getIpReplica() {
        return ipReplica;
    }

    public void setIpReplica(String ipReplica) throws InvalidReplicaException {
        if (!ipReplica.matches(IP_VERIFICATION) || ipReplica.equals(""))
            throw new InvalidReplicaException("Invalid Server Exception IP");

        this.ipReplica = ipReplica;
    }

    public int getCopiasEBookArraySize(){
        return this.copiasEBookArrayList.size();
    }

    public String getLocalizacao_Pais_ReplicaServidor() {
        return localizacao_Pais_ReplicaServidor;
    }

    public void setLocalizacao_Pais_ReplicaServidor(String localizacao_Pais_ReplicaServidor) throws InvalidReplicaException {
        if (localizacao_Pais_ReplicaServidor == null || localizacao_Pais_ReplicaServidor.chars().allMatch(Character::isDigit) || localizacao_Pais_ReplicaServidor.equals(""))
            throw new InvalidReplicaException("Invalid Server Exception");

        this.localizacao_Pais_ReplicaServidor = localizacao_Pais_ReplicaServidor;
    }

    public String getLocalizacaoReplica() {
        return localizacao_Pais_ReplicaServidor;
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

}
