import Aplicacao.*;
import Aplicacao.Exceptions.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUnidade_Vizualizar {


    private Integer id_emp = 1;
    private LocalDate dataHoraEmp = LocalDate.now();
    private LocalDate FimdataHoraEmp = LocalDate.now().plusMonths(1);
    private Utilizador user = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Aveiro, Portugal","121-231-123","ativo");
    private EBook eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
    private CopiaEBook copiaEBook = new CopiaEBook(1,eBook);
    private ReplicaProximaUser server = new ReplicaProximaUser();
    private ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro","Portugal");


    public TestUnidade_Vizualizar() throws InvalidUserException, InvalidCopiaEBookException, InvalidReplicaException, InvalidEBookException, EmprestimoException {
    }


    @Test
    void test_Vizualizar_() throws ExtensaoEmprestimoException, EmprestimoException, InvalidUserException, NotAllowedToReadException {
        Utilizador userInativo = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Aveiro, Portugal","121-231-123","desativo");
        Utilizador userAtivo = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Aveiro, Portugal","121-231-123","ativo");
        Emprestimo empAtivo = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp, userAtivo, eBook,1);
        Emprestimo empInativo = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp, userInativo, eBook,1);
        Visualizar visualizar = new Visualizar();

        //VV -> as duas condições entram no if
        assertThrows(NotAllowedToReadException.class, () -> {
            visualizar.verificarPodeLer(empInativo, empInativo.getFimdataHoraEmp().plusMonths(50));
        });
        //VF -> A condição utilizador está inativo é ativada
        assertThrows(NotAllowedToReadException.class, () -> {
            visualizar.verificarPodeLer(empInativo, empInativo.getDataHoraEmp());
        });
        //FV -> A condição data superior a limite é ativada
        assertThrows(NotAllowedToReadException.class, () -> {
            visualizar.verificarPodeLer(empAtivo, empAtivo.getFimdataHoraEmp().plusMonths(50));
        });
        //FF
        assertTrue(visualizar.verificarPodeLer(empAtivo, empAtivo.getDataHoraEmp()));


    }

    private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.setOut(new PrintStream(outContent));
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

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.setOut(System.out);
    }

}
