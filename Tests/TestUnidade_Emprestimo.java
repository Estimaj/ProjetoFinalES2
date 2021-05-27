import Aplicacao.*;
import Aplicacao.Exceptions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestUnidade_Emprestimo {

    private Emprestimo emp = null;
    private Integer id_emp = 1;
    private LocalDate dataHoraEmp = LocalDate.now();
    private LocalDate FimdataHoraEmp = LocalDate.now().plusMonths(1);
    private Utilizador user = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Aveiro, Portugal","121-231-123","ativo");
    private EBook eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
    private CopiaEBook copiaEBook = new CopiaEBook(1,eBook);
    private ReplicaProximaUser server = new ReplicaProximaUser();
    private ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro","Portugal");
    private Utilizador user_desativo = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Aveiro, Portugal","121-231-123","desativo");

    public TestUnidade_Emprestimo() throws InvalidUserException, InvalidCopiaEBookException, InvalidReplicaException, InvalidEBookException {
    }

    @Test
    void test_Criacao_Emprestimo() throws EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
        assertNotNull(emp);
    }

    @Test
    void test_Criacao_Emprestimo_Com_User_Desativo() throws InvalidUserException {
        user = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Aveiro, Portugal","121-231-123","desativado");
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
        });
    }

    @Test
    void test_Criacao_Emprestimo_com_null_params() throws EmprestimoException {
        assertThrows(NullPointerException.class, () -> {
            emp = new Emprestimo(0,null,null,null,null,1);
        });
    }

    @Test
    void test_setIdEmprestimo() throws EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
        assertEquals(id_emp,emp.getId_emp());
    }

    @Test
    void test_setDataHoraEmp() throws EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
        assertEquals(dataHoraEmp,emp.getDataHoraEmp());
    }

    @Test
    void test_setFimdataHoraEmp() throws EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook, 1);
        assertEquals(FimdataHoraEmp,emp.getFimdataHoraEmp());
    }

    @Test
    void createEmprestimoInitialDateEqualstoFinalDate() {
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(id_emp,LocalDate.now(),LocalDate.now(),user, eBook, 1);
        });
    }

    @Test
    void test_setextensaoEmprestimo() throws ExtensaoEmprestimoException, EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook, 1);
        emp.extenderEmprestimo();
        assertEquals(1,emp.getExtensaoEmprestimo());
        LocalDate extensaoEmprestimo = this.FimdataHoraEmp.plusMonths(1);
        assertEquals(extensaoEmprestimo,emp.getFimdataHoraEmp());
    }
    @Test
    void test_setextensaoEmprestimo_2_vezes() throws ExtensaoEmprestimoException, EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook, 1);
        emp.extenderEmprestimo();
        emp.extenderEmprestimo();
        assertEquals(2,emp.getExtensaoEmprestimo());
        LocalDate extensaoEmprestimo = this.FimdataHoraEmp.plusMonths(2);
        assertEquals(extensaoEmprestimo,emp.getFimdataHoraEmp());
    }

    @Test
    void test_setextensaoEmprestimo_Exception() throws ExtensaoEmprestimoException, EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook, 1);
        emp.extenderEmprestimo();
        assertEquals(1,emp.getExtensaoEmprestimo());
    }

    @Test
    void CreateEmprestimoOK() throws EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
        assertEquals(1,emp.getAssinaturaTR());
    }

    @Test
    void CreateEmprestimoTRWrong() {
        assertThrows(InvalidUserException.class, () -> {
            emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook ,0);
        });
    }

    @Test
    void test_BlackBox_Particionamento_Extensao_de_Emprestimo() throws ExtensaoEmprestimoException {
        /*
        *   fixme
        *       perceber inputs possiveis
        *       perceper outputs expectaveis
        *
        *       |		|		|		|
		*		        0		2
		*
		*       valores entre 0 e 2
		*       valores apartir de 2
		*       valores abaixo de 0
		*
        * */

        //Testes aqui
    }

    @Test
    void test_BlackBox_Valores_Limite_Extensao_de_Emprestimo() throws ExtensaoEmprestimoException {
        /*
         *   fixme
         *       perceber inputs possiveis
         *       perceper outputs expectaveis
         *
         *       |		|		|		|
         *		        0		2
         *
         *       escolher os valores 0 e 2 para o Test
         *       escolher os valores limite [-1 , 0 , 1 , 2 , 5  ]
         *
         * */

        //Testes aqui
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
