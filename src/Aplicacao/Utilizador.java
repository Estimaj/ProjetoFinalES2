package Aplicacao;

public class Utilizador {

    private Integer id_utilizador;
    private String nome_utilizador;
    private String email_utilizador;
    private String morada_utilizador;
    private String telefone_utilizador;
    private String estado_utilizador;

    public Utilizador(Integer id_utilizador, String nome_utilizador, String email_utilizador, String morada_utilizador, String telefone_utilizador, String estado_utilizador) {
        this.id_utilizador = id_utilizador;
        this.nome_utilizador = nome_utilizador;
        this.email_utilizador = email_utilizador;
        this.morada_utilizador = morada_utilizador;
        this.telefone_utilizador = telefone_utilizador;
        this.estado_utilizador = estado_utilizador;
    }

    public Integer getId_utilizador() {
        return id_utilizador;
    }

    public void setId_utilizador(Integer id_utilizador) {
        this.id_utilizador = id_utilizador;
    }

    public String getNome_utilizador() {
        return nome_utilizador;
    }

    public void setNome_utilizador(String nome_utilizador) {
        this.nome_utilizador = nome_utilizador;
    }

    public String getEmail_utilizador() {
        return email_utilizador;
    }

    public void setEmail_utilizador(String email_utilizador) {
        this.email_utilizador = email_utilizador;
    }

    public String getMorada_utilizador() {
        return morada_utilizador;
    }

    public void setMorada_utilizador(String morada_utilizador) {
        this.morada_utilizador = morada_utilizador;
    }

    public String getTelefone_utilizador() {
        return telefone_utilizador;
    }

    public void setTelefone_utilizador(String telefone_utilizador) {
        this.telefone_utilizador = telefone_utilizador;
    }

    public String getEstado_utilizador() {
        return estado_utilizador;
    }

    public void setEstado_utilizador(String estado_utilizador) {
        this.estado_utilizador = estado_utilizador;
    }
}
