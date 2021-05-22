package Aplicacao;

public class ReplicaServidor {
    private String localizacao_Cidade_ReplicaServidor;
    private CopiaEBook copiaEBook;

    public ReplicaServidor(String localizacao_Cidade_ReplicaServidor, CopiaEBook copiaEBook) {
        this.localizacao_Cidade_ReplicaServidor = localizacao_Cidade_ReplicaServidor;
        this.copiaEBook = copiaEBook;
    }

    public String getLocalizacao_Cidade_ReplicaServidor() {
        return localizacao_Cidade_ReplicaServidor;
    }

    public void setLocalizacao_Cidade_ReplicaServidor(String localizacao_Cidade_ReplicaServidor) {
        this.localizacao_Cidade_ReplicaServidor = localizacao_Cidade_ReplicaServidor;
    }

    public CopiaEBook getCopiaEBook() {
        return copiaEBook;
    }

    public void setCopiaEBook(CopiaEBook copiaEBook) {
        this.copiaEBook = copiaEBook;
    }
}
