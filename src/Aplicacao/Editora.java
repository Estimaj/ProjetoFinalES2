package Aplicacao;

import Aplicacao.Exceptions.InvalidEditoraException;

public class Editora {

    private int id_editora;
    private String nomeEditora;

    public Editora(int id_editora, String nomeEditora) throws InvalidEditoraException {

        if (id_editora <= 0 || id_editora >= 30001)
            throw new InvalidEditoraException("Editora invalido Id");

        if (nomeEditora == null || nomeEditora.equals("") || nomeEditora.length() > 20)
            throw new InvalidEditoraException("Invalid Editora Exception");

        this.id_editora = id_editora;
        this.nomeEditora = nomeEditora;
    }

    public int getId_editora() {
        return id_editora;
    }

    public String getNomeEditora() {
        return nomeEditora;
    }

    public void setId_editora(int id_editora) {
        this.id_editora = id_editora;
    }

    public void setNomeEditora(String nomeEditora) {
        this.nomeEditora = nomeEditora;
    }
}
