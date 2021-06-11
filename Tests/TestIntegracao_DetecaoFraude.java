import Aplicacao.*;
import Aplicacao.Exceptions.*;
import apagar.Detecao.DetecaoFraude;
import apagar.Detecao.DetecaoFraudeInterface;
import apagar.Detecao.DetecaoFraudeStub;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestIntegracao_DetecaoFraude {

    private LocalDate dataHoraEmp = LocalDate.now();
    private LocalDate FimdataHoraEmp = LocalDate.now().plusMonths(1);
    private Utilizador user = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Aveiro, Portugal","121-231-123","ativo");
    private EBook eBook = new EBook(1,"akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
    private Emprestimo emp =  new Emprestimo(1,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
    private Funcionario func = new Funcionario(1,"Joao","Joao@exemplo.com","JoAo12!");
    private DetecaoFraude1 detecaoFraude = new DetecaoFraude1(1,user,func);

    public TestIntegracao_DetecaoFraude() throws InvalidUserException, InvalidEBookException, InvalidEBookSizeException, InvalidEBookFormatException, EmprestimoException, InvalidDetecaoFraudeException {
    }

    @Test
    void createConstructorDetecaoFraudeOK() {

    }

    @Test
    void createDetecaoFraudeNull() {

    }

    @Test
    void createDetecaoFraudeWithNullUser() {

    }

    @Test
    void createDetecaoFraudeOK() throws UtilizadorNullException, InvalidUserException, InvalidDetecaoFraudeException, EmprestimoException {


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
