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
    private Utilizador user = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Portugal","121-231-123","ativo");
    private EBook eBook = new EBook(1,"akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
    private CopiaEBook copiaEBook = new CopiaEBook(1,eBook);



    public TestUnidade_Visualizar() throws InvalidUserException, InvalidEBookFormatException, InvalidEBookSizeException, InvalidEBookException, InvalidCopiaEBookException {
    }


    @Test
    void VisualizarOK() throws EmprestimoException, InvalidUserException, NotAllowedToReadException {
        Utilizador userAtivo = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Portugal","121-231-123","ativo");
        Emprestimo empAtivo = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp, userAtivo, copiaEBook,1);
        Visualizar visualizar = new Visualizar();

        //VV -> as duas condições entram no if
        assertTrue(visualizar.verificarPodeLer(empAtivo, empAtivo.getFimdataHoraEmp()));
    }

    @Test
    void Visualizar_When_Date_is_Right_InvalidUser() throws EmprestimoException, InvalidUserException {
        Utilizador userAtivo = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Portugal","121-231-123","ativo");
        Emprestimo empAtivo = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp, userAtivo, copiaEBook,1);
        Visualizar visualizar = new Visualizar();


        //VF -> A condição utilizador está inativo é ativada
        assertThrows(NotAllowedToReadException.class, () -> {
            empAtivo.getUtilizador().setEstado_utilizador("desativado");
            visualizar.verificarPodeLer(empAtivo, empAtivo.getDataHoraEmp());
        });
    }

    @Test
    void Visualizar_When_Date_isAfter_Fim_Emprestimo_ValidUser() throws EmprestimoException, InvalidUserException {
        Utilizador userAtivo = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Portugal","121-231-123","ativo");
        Emprestimo empAtivo = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp, userAtivo, copiaEBook,1);
        Visualizar visualizar = new Visualizar();

        //FV -> A condição data superior a limite é ativada
        assertThrows(NotAllowedToReadException.class, () -> {
            visualizar.verificarPodeLer(empAtivo, empAtivo.getFimdataHoraEmp().plusMonths(50));
        });
    }


    @Test
    void Visualizar_When_Date_is_After_Fim_Emprestimo_and_InvalidUser() throws EmprestimoException, InvalidUserException {
        Utilizador userAtivo = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Portugal","121-231-123","ativo");
        Emprestimo empAtivo = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp, userAtivo, copiaEBook,1);
        Visualizar visualizar = new Visualizar();

        //FF
        empAtivo.getUtilizador().setEstado_utilizador("desativado");
        assertThrows(NotAllowedToReadException.class, () -> {
            visualizar.verificarPodeLer(empAtivo, empAtivo.getDataHoraEmp().plusMonths(50));
        });
    }



    @Test
    void Visualizar_When_Date_is_Before_Fim_Emprestimo() throws EmprestimoException, InvalidUserException {
        Utilizador userAtivo = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Portugal","121-231-123","ativo");
        Emprestimo empAtivo = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp, userAtivo, copiaEBook,1);
        Visualizar visualizar = new Visualizar();

        assertThrows(NotAllowedToReadException.class, () -> {
            visualizar.verificarPodeLer(empAtivo, empAtivo.getDataHoraEmp().minusMonths(2));
        });


    }

    @Test
    void Visualizar_When_Date_is_Before_FimEmprestimo_InvalidUser() throws EmprestimoException, InvalidUserException {
        Utilizador userAtivo = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Portugal","121-231-123","ativo");
        Emprestimo empAtivo = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp, userAtivo, copiaEBook,1);
        Visualizar visualizar = new Visualizar();


        assertThrows(NotAllowedToReadException.class, () -> {
            userAtivo.setEstado_utilizador("desativado");
            visualizar.verificarPodeLer(empAtivo, empAtivo.getDataHoraEmp().minusMonths(2));
        });


    }



    @BeforeAll
    static void setUpBeforeClass() {
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