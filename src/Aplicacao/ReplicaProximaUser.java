package Aplicacao;

import java.util.ArrayList;

public class ReplicaProximaUser {
    private ArrayList<ReplicaServidor> replicasArrayList = new ArrayList<>();

    public ReplicaProximaUser() {
    }

    public void addReplica(ReplicaServidor replicasArrayListervidor) {
        replicasArrayList.add(replicasArrayListervidor);
    }

    public void removeReplica(ReplicaServidor replicasArrayListervidor) {
        replicasArrayList.add(replicasArrayListervidor);
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


    public String show_info_replicas() {
        String to_return = null;
        for (ReplicaServidor replicaServidor : replicasArrayList) {
            for (int j = 0; j < replicaServidor.getcopiasEBookArrayList().size(); j++) {
                to_return = replicaServidor.getLocalizacaoReplica() + "," + replicaServidor.getcopiasEBookArrayList().get(j).getEBook().getTitulo();
            }
        }
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

            partsReplica = replicasArrayList.get(i).getLocalizacaoReplica().split(",");
            cidadeReplica = partsReplica[0];
            paisReplica = partsReplica[1].replaceAll("\\s+", "");

            System.out.println(cidadeReplica + paisReplica);

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
