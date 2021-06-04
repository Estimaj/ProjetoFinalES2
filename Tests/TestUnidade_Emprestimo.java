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
    private GestorReplicas gestorReplicas = new GestorReplicas();
    private ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro","Portugal");
    private Utilizador user_desativo = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Aveiro, Portugal","121-231-123","desativado");

    public TestUnidade_Emprestimo() throws InvalidUserException, InvalidCopiaEBookException, InvalidReplicaException, InvalidEBookException, InvalidEBookSizeException, InvalidEBookFormatException {
    }

    @Test
    void CreateEmprestimoOK() throws EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
        assertEquals(1,emp.getId_emp());
    }

    @Test
    void CreateEmprestimoWithUserDesativo() throws InvalidUserException {
        user = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Aveiro, Portugal","121-231-123","desativado");
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
        });
    }

    @Test
    void CreateEmprestimoWithNullParams() throws EmprestimoException {
        assertThrows(NullPointerException.class, () -> {
            emp = new Emprestimo(0,null,null,null,null,1);
        });
    }

    @Test
    void CreateEmprestimoSetIdEmp() throws EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
        assertEquals(id_emp,emp.getId_emp());
    }

    @Test
    void CreateEmprestimoSetDataHoraEmp() throws EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
        assertEquals(dataHoraEmp,emp.getDataHoraEmp());
    }

    @Test
    void CreateEmprestimoSetFimDataHoraEmp() throws EmprestimoException {
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
    void createEmprestimoInitialDateisBeforetoFinalDate() {
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(id_emp,LocalDate.now(),LocalDate.now().minusMonths(1),user, eBook, 1);
        });
    }

    @Test
    void CreateEmprestimoTROK() throws EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
        assertEquals(1,emp.getAssinaturaTR());
    }

    @Test
    void CreateEmprestimoTRWrong() {
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook ,0);
        });
    }

    @Test
    void CreateEmprestimoTRDifferent1() {
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook ,2);
        });
    }

    @Test
    void CreateEmprestimoWithEBook() throws EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook ,1);
        assertEquals("The Shinning",emp.getEBook().getTitulo());
    }
    @Test
    void CreateEmprestimoWithEBookNull() {
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, null ,1);
        });
    }

    @Test
    void CreateEmprestimoWithCopiaEBook() throws EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook ,1);
        emp.setCopiaEBook(copiaEBook);
        assertEquals(1,emp.getCopiaEBook().getId());
    }

    @Test
    void CreateEmprestimoWithCopiaEBookNull() throws EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook ,1);
        assertThrows(EmprestimoException.class, () -> {
            emp.setCopiaEBook(null);
        });
    }

    @Test
    void CreateEmprestimoWithReplica() throws EmprestimoException, InvalidReplicaException, InvalidUserException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro", "Portugal");
        replicaServidor_aveiro.addCopiaEBook(copiaEBook);
        ReplicaServidor replicaServidor_guimaraes = new ReplicaServidor("Guimaraes", "Portugal");
        replicaServidor_guimaraes.addCopiaEBook(copiaEBook);
        ReplicaServidor replicaServidor_coimbra = new ReplicaServidor("Coimbra", "Portugal");
        replicaServidor_coimbra.addCopiaEBook(copiaEBook);
        gestorReplicas.addReplica(replicaServidor_aveiro);
        gestorReplicas.addReplica(replicaServidor_guimaraes);
        gestorReplicas.addReplica(replicaServidor_coimbra);

        user = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Aveiro, Portugal","121-231-123","ativo");
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook ,1);
        ReplicaServidor replica = gestorReplicas.get_Replica_Proxima_Cliente(emp);

        emp.setReplicaServidor(replica);
        assertEquals("Aveiro,Portugal",emp.getReplicaServidor().getLocalizacaoReplica());
    }

    @Test
    void CreateEmprestimoWithReplicaNull() throws EmprestimoException {
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook ,1);
        assertThrows(EmprestimoException.class, () -> {
            emp.setReplicaServidor(null);
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
    void Extensao_de_Emprestimo_1_Vez() throws ExtensaoEmprestimoException, EmprestimoException {
        Emprestimo empAtivo = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
        LocalDate test = empAtivo.getFimdataHoraEmp();
        empAtivo.extenderEmprestimo();

        //Ver se aumentou a primeira extensão e se a data atualizou
        assertEquals(1, empAtivo.getExtensaoEmprestimo());
        assertEquals(test.plusMonths(1), empAtivo.getFimdataHoraEmp());

    }

    @Test
    void Extensao_de_Emprestimo_2_Vez() throws ExtensaoEmprestimoException, EmprestimoException {
        Emprestimo empAtivo = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
        LocalDate test = empAtivo.getFimdataHoraEmp();
        empAtivo.extenderEmprestimo();

        empAtivo.extenderEmprestimo();

        //Ver se aumentou a segunda extensão e se a data atualizou
        assertEquals(2,empAtivo.getExtensaoEmprestimo() );
        assertEquals(test.plusMonths(2), empAtivo.getFimdataHoraEmp());


    }

    @Test
    void Extensao_de_Emprestimo_3_Vez() throws ExtensaoEmprestimoException, EmprestimoException {
        Emprestimo empAtivo = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
        LocalDate test = empAtivo.getFimdataHoraEmp();
        empAtivo.extenderEmprestimo();


        empAtivo.extenderEmprestimo();


        //Atirar throw à terceira extensão
        assertThrows(ExtensaoEmprestimoException.class, () -> {
            empAtivo.extenderEmprestimo();
        });
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
