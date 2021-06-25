package Aplicacao;
import Aplicacao.Exceptions.InvalidUserException;

import java.util.Objects;


public class Utilizador {

    private Integer id_utilizador;
    private String nome_utilizador;
    private String email_utilizador;
    private String pwd_utilizador;
    private String morada_utilizador;
    private String telefone_utilizador;
    private String estado_utilizador;
    private static final String EMAIL_VERIFICATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    private static final String PWD_VERIFICATION = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";
    private static final String PHONE_VERIFICATION = "[0-9]{3}[-]?[0-9]{3}[-]?[0-9]{3}";
    private static final String NUMBER_VERIFICATION = ".*\\d.*";

    public Utilizador(Integer id_utilizador, String nome_utilizador, String email_utilizador, String pwd_utilizador,String morada_utilizador, String telefone_utilizador, String estado_utilizador) throws InvalidUserException {

        if (id_utilizador <= 0 || id_utilizador >= 30001)
            throw new InvalidUserException("Utilizador invalido Id");

        if (nome_utilizador == null || nome_utilizador.matches(NUMBER_VERIFICATION) || nome_utilizador.equals("") || nome_utilizador.length() > 15)
            throw new InvalidUserException("Utilizador invalido nome");

        if (email_utilizador == null || !email_utilizador.matches(EMAIL_VERIFICATION))
            throw new InvalidUserException("Utilizador invalido em");

        if (pwd_utilizador == null || !pwd_utilizador.matches(PWD_VERIFICATION) || pwd_utilizador.equals(""))
            throw new InvalidUserException("Utilizador invalido pwd");

        if (pwd_utilizador.length() <= 7 || pwd_utilizador.length() >= 21)
            throw new InvalidUserException("Utilizador invalido pwd" +
                    "\nNumero de caracteres errados");

        if (telefone_utilizador == null || !telefone_utilizador.matches(PHONE_VERIFICATION))
            throw new InvalidUserException("Utilizador invalido telefone");

        if (!Objects.equals(estado_utilizador, "ativo") && !Objects.equals(estado_utilizador,"desativado") || estado_utilizador.matches(NUMBER_VERIFICATION) || estado_utilizador == null)
            throw new InvalidUserException("Utilizador invalido estado");

        if (!morada_utilizador.contains(",") || morada_utilizador.matches(NUMBER_VERIFICATION))
            throw new InvalidUserException("Utilizador Invalido Morada");

        this.id_utilizador = id_utilizador;
        this.nome_utilizador = nome_utilizador;
        this.email_utilizador = email_utilizador;
        this.pwd_utilizador = pwd_utilizador;
        this.morada_utilizador = morada_utilizador;
        this.telefone_utilizador = telefone_utilizador;
        this.estado_utilizador = estado_utilizador;
    }


    public Integer getId_utilizador() {
        return id_utilizador;
    }

    public void setId_utilizador(Integer id_utilizador) throws InvalidUserException {
        if (id_utilizador <= 0)
            throw new InvalidUserException("Utilizado invalido Id");
        this.id_utilizador = id_utilizador;
    }

    public String getNome_utilizador() {
        return nome_utilizador;
    }

    public void setNome_utilizador(String nome_utilizador) throws InvalidUserException {
        if (nome_utilizador == null || nome_utilizador.chars().allMatch( Character::isDigit ))
            throw new InvalidUserException("Utilizado invalido nome");
        this.nome_utilizador = nome_utilizador;
    }

    public String getEmail_utilizador() {
        return email_utilizador;
    }

    public void setEmail_utilizador(String email_utilizador) throws InvalidUserException {
        if (email_utilizador == null || !email_utilizador.matches(EMAIL_VERIFICATION))
            throw new InvalidUserException("Utilizado invalido em");
        this.email_utilizador = email_utilizador;
    }

    public String getPwd_utilizador() {
        return pwd_utilizador;
    }

    public void setPwd_utilizador(String pwd_utilizador) throws InvalidUserException {
        if (pwd_utilizador == null || !pwd_utilizador.matches(PWD_VERIFICATION))
            throw new InvalidUserException("Utilizado invalido pwd");
        this.pwd_utilizador = pwd_utilizador;
    }

    public String getMorada_utilizador() {
        return morada_utilizador;
    }

    public void setMorada_utilizador(String morada_utilizador) throws InvalidUserException {
        if (!morada_utilizador.contains(","))
            throw new InvalidUserException("Utilizador Invalido Morada");
        this.morada_utilizador = morada_utilizador;
    }

    public String getTelefone_utilizador() {
        return telefone_utilizador;
    }

    public void setTelefone_utilizador(String telefone_utilizador) throws InvalidUserException {
        if (telefone_utilizador == null || !telefone_utilizador.matches(PHONE_VERIFICATION))
            throw new InvalidUserException("Utilizado invalido telefone");
        this.telefone_utilizador = telefone_utilizador;
    }

    public String getEstado_utilizador() {
        return estado_utilizador;
    }

    public void setEstado_utilizador(String estado_utilizador) throws InvalidUserException {
        if (!estado_utilizador.equals("ativo") && !estado_utilizador.equals("desativado"))
            throw new InvalidUserException("Utilizador invalido => estado");

        this.estado_utilizador = estado_utilizador;
    }

    public boolean autenticacao(String em, String pw){
        return this.getEmail_utilizador().equals(em) && this.getPwd_utilizador().equals(pw);
    }
}
