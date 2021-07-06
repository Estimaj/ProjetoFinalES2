import Aplicacao.Exceptions.InvalidFuncException;
import Aplicacao.Exceptions.InvalidUserException;
import Aplicacao.Funcionario;
import Aplicacao.Utilizador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUnidade_Funcionario {

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
    void createfuncWithIdLess30001() throws InvalidFuncException {
        id_func = 30000;
        func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        assertEquals(id_func,func.getId_func());
    }

    @Test
    void createfuncWithIdEquals30001() {
        id_func = 30001;
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }

    @Test
    void createfuncWithGreat30001()  {
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(30002,nome_func,email_func,pwd_func);
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
    void createfuncWithWrongIdGreat0() throws InvalidFuncException {
        id_func = 1;
        func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        assertEquals(id_func,func.getId_func());
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
    void createFuncWithInvalidEmail_Exceeds_Max_Email_Lenght() {
        email_func = "ines.exemploines.exemploines.exemploines.exemploines.exemplo@pt";
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }

    @Test
    void createFuncWithInvalidEmail_Equals_Minimum_Email_Lenght() {
        email_func = "";
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }

    @Test
    void createFuncWithInvalidEmail_Null() {
        email_func = null;
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
    void createFuncWithEmptyPwd() {
        pwd_func = "";
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }

    @Test
    void createfuncWithInvalidPwdLenghLessThenMinimum() {
        pwd_func = "Abc1aB";
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }

    @Test
    void createfuncWithInvalidPwdLenghLessEqualsToMinimum() {
        pwd_func = "Abc1abC";
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }

    @Test
    void createfuncWithInvalidPwdLenghAboveMaximum() {
        pwd_func = "Abc1aBAbc1aBAbc1aBAbc1aB";
        assertThrows(InvalidFuncException.class, () -> {
            func = new Funcionario(id_func,nome_func,email_func,pwd_func);
        });
    }

    @Test
    void createfuncWithInvalidPwdLenghEqualsToMaximum() {
        pwd_func = "Abc1abCabCabCabCabCabC";
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
