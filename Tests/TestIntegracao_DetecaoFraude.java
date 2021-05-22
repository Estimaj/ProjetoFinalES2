import Aplicacao.*;
import Detecao.DetecaoFraudeInterface;
import Detecao.DetecaoFraudeStub;
import Aplicacao.Exceptions.EmprestimoException;
import Aplicacao.Exceptions.UtilizadorNullException;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestIntegracao_DetecaoFraude {

    private String desativado = "desativado";
    private String ativado = "ativo";
    private Utilizador u = new Utilizador(1,"Clark","Clark@exemplo.pt","Krypton","111","ativo");
    private Utilizador user_desativo = new Utilizador(2,"Phoebe","Phoebe@exemplo.pt","Rua da Phoebe","111","desativo");
    private EBook eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
    private CopiaEBook copiaEBook = new CopiaEBook(1,eBook);
    private Emprestimo emp = null;
    private DetecaoFraudeInterface detecaoFraudeInterface = null;
    private DetecaoFraudeInterface detecaoFraudeInterfaceStub = new DetecaoFraudeStub();;


    @Test
    void test_detecao_fraude_not_null(){
        assertNotNull(detecaoFraudeInterfaceStub);
    }

    @Test
    void test_detecao_fraude_null(){
        assertNull(detecaoFraudeInterface);
    }

    @Test
    void test_detecao_fraude_with_User() {
        Throwable exception = assertThrows(UtilizadorNullException.class, () -> {
            detecaoFraudeInterfaceStub.detecao_fraude(null);
        });
        assertEquals(UtilizadorNullException.class, exception.getClass());
    }

    @Test
    void test_detecao_fraude() throws UtilizadorNullException {
        if (detecaoFraudeInterfaceStub.detecao_fraude(u) == 1)
            assertEquals(desativado,u.getEstado_utilizador());
        else
            assertEquals(ativado,u.getEstado_utilizador());
    }

    @Test
    void test_Emprestimo_Conta_Cancelada() {
        //so apanha quando o user esta cancelado
        System.out.println("test_Emprestimo_Conta_Cancelada ==> " + user_desativo.getEstado_utilizador());
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        Server server = new Server("Portugal");
        server.addReplica(replicaServidor_aveiro);
        Throwable exception = assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(1,LocalDate.now(),LocalDate.now().plusMonths(1),user_desativo, copiaEBook, replicaServidor_aveiro,1);
        });
        assertEquals(EmprestimoException.class, exception.getClass());
    }

    @BeforeAll
    static void set(){
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
