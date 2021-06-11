import Aplicacao.Exceptions.InvalidFuncException;
import Aplicacao.Funcionario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFuncionario {

    private Funcionario func = null;
    private Integer id_func = 1;
    private String nome_func = "Ines";
    private String email_func= "ines@exemplo.com";
    private String pwd_func = "Abc1abcABC";


    @Test
    void createFuncOk() throws InvalidFuncException {
        func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        assertNotNull(func);
    }

    @Test
    void createFuncNull(){
        assertNull(func);
    }

    @Test
    void createfuncNullConstructor() {
        assertThrows(NullPointerException.class, () -> {
            func = new Funcionario(null,null,null,null);
        });
    }

    @Test
    void createfuncWithOKId() throws InvalidFuncException {
        func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        assertEquals(id_func,func.getId_func());
    }

    @Test
    void createfuncWithGreatMax()  {
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(30001,nome_func,email_func,pwd_func);
        });
    }

    @Test
    void createfuncWithWrongIdEquals0() {
        id_func = 0;
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }

    @Test
    void createfuncWithWrongIdLess0() {
        id_func = -1;
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }


    @Test
    void createfuncWithOKName() throws InvalidFuncException {
        func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        assertEquals(nome_func,func.getNome_func());
    }
    @Test
    void createfuncWithNullName() {
        nome_func = null;
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }
    @Test
    void createfuncWithEmptyName() {
        nome_func = "";
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }

    @Test
    void createFuncWithNameLengthAboveMax() {
        nome_func = "InêsJustrianaPereira";
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }
    @Test
    void createfuncWithInvalidNameNumber() {
        nome_func = "1";
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }
    @Test
    void createfuncWithValidEmail() throws InvalidFuncException {
        func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        assertEquals(email_func,func.getEmail_func());
    }

    @Test
    void createfuncWithInvalidEmail() {
        email_func = "ines.exemplo.pt";
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }

    @Test
    void createfuncWithValidPwd() throws InvalidFuncException {
        func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        assertEquals(pwd_func,func.getPwd_func());
    }

    @Test
    void createfuncWithInvalidPwd() {
        pwd_func = "ines";
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }

    @Test
    void createfuncWithInvalidNullPwd() {
        pwd_func = null;
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }


}
