package Aplicacao;

import Aplicacao.Exceptions.EmprestimoException;

import java.util.ArrayList;

public class GestorReplicas {
    private ArrayList<ReplicaServidor> replicasArrayList = new ArrayList<>();

    public GestorReplicas() {
    }

    public void addReplica(ReplicaServidor replicaServidor) {
        replicasArrayList.add(replicaServidor);
    }

    public void removeReplica(ReplicaServidor replicaServidor) {
        replicasArrayList.remove(replicaServidor);
    }


    public CopiaEBook getCopia_of_Replica(CopiaEBook copiaEBook) {
        for (int i = 0; i < replicasArrayList.size(); i++) {
            for (int j = 0; j < replicasArrayList.get(i).getcopiasEBookArrayList().size(); j++) {
                if (copiaEBook.equals(replicasArrayList.get(i).getcopiasEBookArrayList().get(j)))
                    return replicasArrayList.get(i).getcopiasEBookArrayList().get(j);
            }
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

    public ArrayList<ReplicaServidor> getreplicasArrayList() {
        return replicasArrayList;
    }

    public int get_replicas_ArrayList_Size() {
        return this.replicasArrayList.size();
    }

    public ReplicaServidor get_Replica_Proxima_Cliente(Emprestimo emp) throws EmprestimoException {

        String paisUser = emp.getUtilizador().getMorada_utilizador();
        String paisReplica;
        ReplicaServidor rp;

        if (replicasArrayList.size() == 0)
            return null;
        if (replicasArrayList.size() == 1)
            return replicasArrayList.get(0);


        for (int i = 0; i < replicasArrayList.size(); i++) {
            for (int j = 0; j < replicasArrayList.get(i).getcopiasEBookArrayList().size(); j++) {
                paisReplica = replicasArrayList.get(i).getLocalizacaoReplica();
                if (replicasArrayList.get(i).getcopiasEBookArrayList().get(j).equals(emp.getCopiaEBook())) {
                    // se a replica for do mesmo pais e tiver a copia
                    if (paisReplica.equals(paisUser)) {
                        return replicasArrayList.get(i);
                    }
                }
            }
        }

        rp = getReplicaServidorWhenAddressDoesntExist(emp);

        return rp;
    }

    private ReplicaServidor getReplicaServidorWhenAddressDoesntExist(Emprestimo emp) {
        //Devolve sempre a primeira replica que encontrar
        ReplicaServidor rp = null;
        boolean loopBreaker = false;
        for (ReplicaServidor replicaServidor : replicasArrayList) {

            if (loopBreaker)
                break;

            for (int j = 0; j < replicaServidor.getcopiasEBookArrayList().size(); j++) {

                if (replicaServidor.getcopiasEBookArrayList().get(j).equals(emp.getCopiaEBook())) {
                    rp = replicaServidor;
                    loopBreaker = true;
                    break;

                }
            }
        }
        return rp;
    }

}
