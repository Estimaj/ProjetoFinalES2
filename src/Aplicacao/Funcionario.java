package Aplicacao;

import Aplicacao.Exceptions.InvalidFuncException;


public class Funcionario {

    private Integer id_func;
    private String nome_func;
    private String email_func;
    private String pwd_func;
    private static final String EMAIL_VERIFICATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    private static final String PWD_VERIFICATION = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";
    private static final String NUMBER_VERIFICATION = ".*\\d.*";


    public Funcionario(Integer id_func, String nome_func, String email_func, String pwd_func) throws InvalidFuncException {

        if (id_func <= 0 || id_func > 30000)
            throw new InvalidFuncException("Func invalido Id");

        if (nome_func == null || nome_func.matches(NUMBER_VERIFICATION) || nome_func.equals(""))
            throw new InvalidFuncException("Func invalido nome");

        if (email_func == null || !email_func.matches(EMAIL_VERIFICATION) || email_func.equals(""))
            throw new InvalidFuncException("Func invalido em");

        if (pwd_func == null || !pwd_func.matches(PWD_VERIFICATION) || pwd_func.equals(""))
            throw new InvalidFuncException("Func invalido pwd");

        this.id_func = id_func;
        this.nome_func = nome_func;
        this.email_func = email_func;
        this.pwd_func = pwd_func;
    }

    public Integer getId_func() {
        return id_func;
    }

    public void setId_func(Integer id_func) {
        this.id_func = id_func;
    }

    public String getNome_func() {
        return nome_func;
    }

    public void setNome_func(String nome_func) {
        this.nome_func = nome_func;
    }

    public String getEmail_func() {
        return email_func;
    }

    public void setEmail_func(String email_func) {
        this.email_func = email_func;
    }

    public String getPwd_func() {
        return pwd_func;
    }

    public void setPwd_func(String pwd_func) {
        this.pwd_func = pwd_func;
    }
}
