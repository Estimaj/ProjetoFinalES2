import Aplicacao.Exceptions.InvalidUserException;
import Aplicacao.Utilizador;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUnidade_Utilizador {

    private Utilizador user = null;
    private Integer id_user = 1;
    private String nome_user = "Maria";
    private String email_user = "maria@exemplo.com";
    private String pwd_user = "Abc1abcABC";
    private String morada_user = "Coimbra, Portugal";
    private String telefone_user = "121-231-123";
    private String estado_user = "ativo";


    @Test
    void createUserOk() throws InvalidUserException {
        user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        assertNotNull(user);
    }

    @Test
    void createUserNull(){
        assertNull(user);
    }

    @Test
    void createUserNullConstructor() {
        assertThrows(NullPointerException.class, () -> {
            user = new Utilizador(null,null,null,null,null,null,null);
        });
    }

    @Test
    void createUserWithOKId() throws InvalidUserException {
        user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        assertEquals(id_user,user.getId_utilizador());
    }

    @Test
    void createUserWithWrongIdEquals0() {
        id_user = 0;
        assertThrows(InvalidUserException.class, () -> {
            user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        });
    }

    @Test
    void createUserWithWrongIdLess0() {
        id_user = -1;
        assertThrows(InvalidUserException.class, () -> {
            user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        });
    }


    @Test
    void createUserWithOKName() throws InvalidUserException {
        user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        assertEquals(nome_user,user.getNome_utilizador());
    }

    @Test
    void createUserWithNullName() {
        nome_user = "";
        assertThrows(InvalidUserException.class, () -> {
            user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        });
    }

    @Test
    void createUserWithInvalidNameNumber() {
        nome_user = "1";
        assertThrows(InvalidUserException.class, () -> {
            user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        });
    }
    @Test
    void createUserWithValidEmail() throws InvalidUserException {
        user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        assertEquals(email_user,user.getEmail_utilizador());
    }

    @Test
    void createUserWithInvalidEmail() {
        email_user = "maria.exemplo.pt";
        assertThrows(InvalidUserException.class, () -> {
            user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        });
    }

    @Test
    void createUserWithValidPwd() throws InvalidUserException {
        user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        assertEquals(pwd_user,user.getPwd_utilizador());
    }

    @Test
    void createUserWithInvalidPwd() {
        pwd_user = "maria";
        assertThrows(InvalidUserException.class, () -> {
            user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        });
    }

    @Test
    void createUserWithInvalidNullPwd() {
        pwd_user = null;
        assertThrows(InvalidUserException.class, () -> {
            user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        });
    }

    @Test
    void createUserWithValidAddress() throws InvalidUserException {
        user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        assertEquals(morada_user,user.getMorada_utilizador());
    }

    @Test
    void createUserWithInvalidAddress() {
        morada_user = "Rua do Esquecimento";
        assertThrows(InvalidUserException.class, () -> {
            user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        });
    }

    @Test
    void createUserWithInvalidAddressNumber() {
        morada_user = "1212";
        assertThrows(InvalidUserException.class, () -> {
            user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        });
    }

    @Test
    void createUserWithNullTelefone() {
        telefone_user = null;
        assertThrows(InvalidUserException.class, () -> {
            user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        });
    }

    @Test
    void createUserWithOKTelefone() throws InvalidUserException {
        user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        assertEquals(telefone_user,user.getTelefone_utilizador());
    }

    @Test
    void createUserWithInvalidTelefone() {
        telefone_user = "123 121 231 223";
        assertThrows(InvalidUserException.class, () -> {
            user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        });
    }


    @Test
    void createUserWithValidEstado() throws InvalidUserException {
        user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        assertEquals(estado_user,user.getEstado_utilizador());
    }

    @Test
    void createUserWithInvalidEstado() {
        estado_user = "a";
        assertThrows(InvalidUserException.class, () -> {
            user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        });
    }

    @Test
    void createUserWithInvalidEstadoNumber() {
        estado_user = "1";
        assertThrows(InvalidUserException.class, () -> {
            user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        });
    }

    @Test
    void createUserWithInvalidEstadoNull() {
        estado_user = null;
        assertThrows(NullPointerException.class, () -> {
            user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
        });
    }

    @Test
    void createUserWithvalidEstadoDesativo() throws InvalidUserException {
        estado_user = "desativo";
        user = new Utilizador(id_user,nome_user,email_user,pwd_user,morada_user,telefone_user,estado_user);
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
