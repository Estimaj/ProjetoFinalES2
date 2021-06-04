import Aplicacao.*;
import Aplicacao.Exceptions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUnidade_Visualizar {


    private Integer id_emp = 1;
    private LocalDate dataHoraEmp = LocalDate.now();
    private LocalDate FimdataHoraEmp = LocalDate.now().plusMonths(1);
    private Utilizador user = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Aveiro, Portugal","121-231-123","ativo");
    private EBook eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
    private CopiaEBook copiaEBook = new CopiaEBook(1,eBook);



    public TestUnidade_Visualizar() throws InvalidUserException, InvalidEBookFormatException, InvalidEBookSizeException, InvalidEBookException, InvalidCopiaEBookException, InvalidReplicaException {
    }


    @Test
    void test_Vizualizar_() throws EmprestimoException, InvalidUserException, NotAllowedToReadException {
        Utilizador userInativo = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Aveiro, Portugal","121-231-123","desativado");
        Utilizador userAtivo = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Aveiro, Portugal","121-231-123","ativo");
        Emprestimo empAtivo = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp, userAtivo, eBook,1);
        Visualizar visualizar = new Visualizar();

        //VV -> as duas condições entram no if
        assertThrows(NotAllowedToReadException.class, () -> {
            empAtivo.getUtilizador().setEstado_utilizador("desativado");
            visualizar.verificarPodeLer(empAtivo, empAtivo.getFimdataHoraEmp().plusMonths(50));
        });
        //VF -> A condição utilizador está inativo é ativada
        assertThrows(NotAllowedToReadException.class, () -> {
            empAtivo.getUtilizador().setEstado_utilizador("desativado");
            visualizar.verificarPodeLer(empAtivo, empAtivo.getDataHoraEmp());
        });
        //FV -> A condição data superior a limite é ativada
        assertThrows(NotAllowedToReadException.class, () -> {
            visualizar.verificarPodeLer(empAtivo, empAtivo.getFimdataHoraEmp().plusMonths(50));
        });
        //FF
        empAtivo.getUtilizador().setEstado_utilizador("ativo");
        assertTrue(visualizar.verificarPodeLer(empAtivo, empAtivo.getDataHoraEmp()));


    }


    @BeforeAll
    static void setUpBeforeClass() throws Exception {
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