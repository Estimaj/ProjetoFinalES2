import Aplicacao.*;
import Aplicacao.Exceptions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestUnidade_DetecaoFraude {

    private Editora editora = new Editora(1,"LEYA");
    private LocalDate dataHoraEmp = LocalDate.now();
    private LocalDate FimdataHoraEmp = LocalDate.now().plusMonths(1);
    private Utilizador user = new Utilizador(1, "Clark", "clark@exemplo.com", "Abc1abcABC", "Portugal", "121-231-123", "ativo");
    private EBook eBook = new EBook(1, "akjshdahq123123", "Stephen King", "The Shinning", editora, "pdf", 150.f, "Stephen king sig");
    private CopiaEBook copiaEBook = new CopiaEBook(1, eBook);
    private Emprestimo emp = new Emprestimo(1, dataHoraEmp, FimdataHoraEmp, user, copiaEBook, 1);
    private Funcionario func = new Funcionario(1, "Joao", "Joao@exemplo.com", "Abc1abcABC!");
    private DetecaoFraude detecaoFraude = new DetecaoFraude(1, emp, func);


    /**
     *  Testes à Deteção de Fraude
     *
     *      BlackBox
     *          Analise de Valores Limite
     *              --> id's
     *
     *      Unidade
     *          Testes quando é criado um emprestimo com um Utilizador desativado
     *
     */


    public TestUnidade_DetecaoFraude() throws InvalidUserException, InvalidEBookException, InvalidEBookSizeException, InvalidEBookFormatException, EmprestimoException, InvalidDetecaoFraudeException, InvalidFuncException, InvalidCopiaEBookException, InvalidEditoraException {
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
    void createDetecaoFraudeIdOk() {
        assertEquals(1, detecaoFraude.getId_detecao());
    }

    @Test
    void createDetecaoFraudeIdEquals0() {
        assertThrows(InvalidDetecaoFraudeException.class, () -> {
            detecaoFraude = new DetecaoFraude(0, emp, func);
        });
    }

    @Test
    void createDetecaoFraudeIdLess0() {
        assertThrows(InvalidDetecaoFraudeException.class, () -> {
            detecaoFraude = new DetecaoFraude(-1, emp, func);
        });
    }

    @Test
    void createDetecaoFraudeIdGreatMax() {
        assertThrows(InvalidDetecaoFraudeException.class, () -> {
            detecaoFraude = new DetecaoFraude(30002, emp, func);
        });
    }

    @Test
    void createDetecaoFraudeIdEqualsMax() {
        assertThrows(InvalidDetecaoFraudeException.class, () -> {
            detecaoFraude = new DetecaoFraude(30001, emp, func);
        });
    }

    @Test
    void createDetecaoFraudeIdLessMax() throws InvalidDetecaoFraudeException, InvalidUserException {
        user.setEstado_utilizador("ativo");
        detecaoFraude = new DetecaoFraude(30000, emp, func);
        assertEquals(30000, detecaoFraude.getId_detecao());

    }

    @Test
    void createDetecaoFraudeWithNullEmp() {
        assertThrows(InvalidDetecaoFraudeException.class, () -> {
            detecaoFraude = new DetecaoFraude(1, null, func);
        });
    }

    @Test
    void createDetecaoFraudeWithNullFunc() {
        assertThrows(InvalidDetecaoFraudeException.class, () -> {
            detecaoFraude = new DetecaoFraude(1, emp, null);
        });
    }

    @Test
    void createDetecaoFraudeGetEstadoUser() throws InvalidDetecaoFraudeException, InvalidUserException {
        emp.getUtilizador().setEstado_utilizador("ativo");
        detecaoFraude = new DetecaoFraude(1, emp, func);
        assertEquals("desativado", emp.getUtilizador().getEstado_utilizador());
    }

    @Test
    void CreateEmprestimoWithCanceledAccount() throws InvalidUserException {
        //so apanha quando o user esta cancelado
        Utilizador user_desativo = new Utilizador(1, "Clark", "Clark@exemplo.pt", "Abc1abcABC", "Portugal", "121-231-123", "desativado");
        System.out.println("test_Emprestimo_Conta_Cancelada ==> " + user_desativo.getEstado_utilizador());

        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(1, LocalDate.now(), LocalDate.now().plusMonths(1), user_desativo, copiaEBook, 1);
        });

        assertEquals("desativado", user_desativo.getEstado_utilizador());
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
