import Aplicacao.*;
import Aplicacao.Exceptions.*;
import apagar.Detecao.DetecaoFraudeInterface;
import apagar.Detecao.DetecaoFraudeStub;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestIntegracao_DetecaoFraude {

    private String desativado = "desativado";
    private String ativado = "ativo";
    private Utilizador u = new Utilizador(1, "Clark", "Clark@exemplo.pt", "Abc1abcABC", "Krypton, Krypton", "111-222-111", "ativo");
    private Utilizador user_desativo = new Utilizador(2, "Phoebe", "phoebe@exemplo.pt", "Abc1abcABC", "Londres, England", "111-222-111", "desativado");
    private EBook eBook = new EBook("akjshdahq123123", "Stephen King", "The Shinning", "Ray Lovejoy", "pdf", 150.f, "Stephen king sig");
    private CopiaEBook copiaEBook = new CopiaEBook(1, eBook);
    private Emprestimo emp = null;
    private DetecaoFraudeInterface detecaoFraudeInterface = null;
    private DetecaoFraudeInterface detecaoFraudeInterfaceStub = new DetecaoFraudeStub();
    private ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro","Portugal");
    private ReplicaProximaUser server = new ReplicaProximaUser();

    public TestIntegracao_DetecaoFraude() throws InvalidUserException, InvalidCopiaEBookException, InvalidEBookException, InvalidReplicaException, InvalidServerException, InvalidEBookSizeException, InvalidEBookFormatException {
    }

    @Test
    void createConstructorDetecaoFraudeOK() {
        assertNotNull(detecaoFraudeInterfaceStub);
    }

    @Test
    void createDetecaoFraudeNull() {
        assertNull(detecaoFraudeInterface);
    }

    @Test
    void createDetecaoFraudeWithNullUser() {
        assertThrows(InvalidDetecaoFraudeException.class, () -> {
            detecaoFraudeInterfaceStub.detecao_fraude(null);
        });
    }

    @Test
    void createDetecaoFraudeOK() throws UtilizadorNullException, InvalidUserException, InvalidDetecaoFraudeException, EmprestimoException {

        server.addReplica(replicaServidor_aveiro);

        if (detecaoFraudeInterfaceStub.detecao_fraude(u)) { //detetou fraude
            assertThrows(EmprestimoException.class, () -> emp = new Emprestimo(1, LocalDate.now(), LocalDate.now().plusMonths(1), u, eBook, 1));
        } else {
            assertEquals(ativado, u.getEstado_utilizador());
            emp = new Emprestimo(1, LocalDate.now(), LocalDate.now().plusMonths(1), u, eBook, 1);
            assertEquals(1,emp.getId_emp());
        }

    }

    @Test
    void EmprestimoWithCanceledAccount() {
        //so apanha quando o user esta cancelado
        System.out.println("test_Emprestimo_Conta_Cancelada ==> " + user_desativo.getEstado_utilizador());
        replicaServidor_aveiro.addCopiaEBook(copiaEBook);
        server.addReplica(replicaServidor_aveiro);
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(1, LocalDate.now(), LocalDate.now().plusMonths(1), user_desativo, eBook,  1);
        });

        assertEquals("desativado",user_desativo.getEstado_utilizador());
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
