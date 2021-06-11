import Aplicacao.*;
import Aplicacao.Exceptions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestIntegracao_DetecaoFraude {

    private LocalDate dataHoraEmp = LocalDate.now();
    private LocalDate FimdataHoraEmp = LocalDate.now().plusMonths(1);
    private Utilizador user = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Aveiro, Portugal","121-231-123","ativo");
    private EBook eBook = new EBook(1,"akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
    private Emprestimo emp =  new Emprestimo(1,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
    private Funcionario func = new Funcionario(1,"Joao","Joao@exemplo.com","Abc1abcABC!");
    private DetecaoFraude1 detecaoFraude = new DetecaoFraude1(1,user,func);

    public TestIntegracao_DetecaoFraude() throws InvalidUserException, InvalidEBookException, InvalidEBookSizeException, InvalidEBookFormatException, EmprestimoException, InvalidDetecaoFraudeException, InvalidFuncException {
    }

    @Test
    void createConstructorDetecaoFraudeOK() {
        assertNotNull(detecaoFraude);
    }

    @Test
    void createDetecaoFraudeNull() {
        detecaoFraude = null;
        assertNull(detecaoFraude);
    }

    @Test
    void createDetecaoFraudeWithNullUser() {
        assertThrows(InvalidDetecaoFraudeException.class, () -> {
            detecaoFraude = new DetecaoFraude1(1,null,func);
        });
    }

    @Test
    void createDetecaoFraudeWithNullFunc() {
        assertThrows(InvalidDetecaoFraudeException.class, () -> {
            detecaoFraude = new DetecaoFraude1(1,user,null);
        });
    }
    @Test
    void createDetecaoFraudeGetEstadoUser() {
        assertEquals("desativado",user.getEstado_utilizador());
    }


    @BeforeAll
    static void set() {
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
