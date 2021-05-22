import Aplicacao.Utilizador;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUnidade_Utilizador {

    private Utilizador user = null;
    private Integer id_user = null;
    private String nome_user = null;
    private String email_user = null;
    private String morada_user = null;
    private String telefone_user = null;
    private String estado_user = null;


    @Test
    void test_Criacao_User(){
        id_user = 1;
        nome_user = "Maria";
        email_user = "Maria@exemplo.pt";
        morada_user = "Rua da Amargura";
        telefone_user = "12123123";
        estado_user = "ativo";
        user = new Utilizador(id_user,nome_user,email_user,morada_user,telefone_user,estado_user);
        assertNotNull(user);
    }

    @Test
    void test_User_null(){
        assertNull(user);
    }

    @Test
    void test_Criacao_User_com_null_params(){
        user = new Utilizador(null,null,null,null,null,null);
        assertNull(user.getId_utilizador());
        assertNull(user.getNome_utilizador());
        assertNull(user.getEmail_utilizador());
        assertNull(user.getMorada_utilizador());
        assertNull(user.getTelefone_utilizador());
        assertNull(user.getEstado_utilizador());
    }

    @Test
    void test_setIdUtilizador(){
        id_user = 2;
        nome_user = "Jarvas";
        email_user = "Jarvas@exemplo.pt";
        morada_user = "Rua da Amargura";
        telefone_user = "12123123";
        estado_user = "ativo";
        user = new Utilizador(id_user,nome_user,email_user,morada_user,telefone_user,estado_user);
        assertEquals(id_user,user.getId_utilizador());
    }


    @Test
    void test_setNomeUtilizador(){
        id_user = 3;
        nome_user = "Clark";
        email_user = "Clark@exemplo.pt";
        morada_user = "Krypton";
        telefone_user = "12123123";
        estado_user = "ativo";
        user = new Utilizador(id_user,nome_user,email_user,morada_user,telefone_user,estado_user);
        assertEquals(nome_user,user.getNome_utilizador());
    }

    @Test
    void test_setEmailUtilizador(){
        id_user = 4;
        nome_user = "Joey";
        email_user = "Joey@exemplo.pt";
        morada_user = "Rua do Esquecimento";
        telefone_user = "12123123";
        estado_user = "ativo";
        user = new Utilizador(id_user,nome_user,email_user,morada_user,telefone_user,estado_user);
        assertEquals(email_user,user.getEmail_utilizador());
    }

    @Test
    void test_setMoradaUtilizador(){
        id_user = 5;
        nome_user = "Phoebe";
        email_user = "Phoebe@exemplo.pt";
        morada_user = "Rua da Phoebe";
        telefone_user = "12123123";
        estado_user = "ativo";
        user = new Utilizador(id_user,nome_user,email_user,morada_user,telefone_user,estado_user);
        assertEquals(morada_user,user.getMorada_utilizador());
    }

    @Test
    void test_setTelefoneUtilizador(){
        id_user = 6;
        nome_user = "Monica";
        email_user = "Monica@exemplo.pt";
        morada_user = "Rua da Monica";
        telefone_user = "12123123";
        estado_user = "ativo";
        user = new Utilizador(id_user,nome_user,email_user,morada_user,telefone_user,estado_user);
        assertEquals(telefone_user,user.getTelefone_utilizador());
    }


    @Test
    void test_setEstadoUtilizador(){
        id_user = 7;
        nome_user = "Chandler";
        email_user = "Chandler@exemplo.pt";
        morada_user = "Rua do Esquecimento";
        telefone_user = "12123123";
        estado_user = "ativo";
        user = new Utilizador(id_user,nome_user,email_user,morada_user,telefone_user,estado_user);
        assertEquals(estado_user,user.getEstado_utilizador());
    }

    @Test
    void test_setEstadoUtilizador_desativo(){
        id_user = 7;
        nome_user = "Chandler";
        email_user = "Chandler@exemplo.pt";
        morada_user = "Rua do Esquecimento";
        telefone_user = "12123123";
        estado_user = "desativo";
        user = new Utilizador(id_user,nome_user,email_user,morada_user,telefone_user,estado_user);
        assertEquals(estado_user,user.getEstado_utilizador());
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown1() {
    }

    @AfterAll
    static void tearDown() {
    }

}
