import Aplicacao.*;
import Aplicacao.Exceptions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUnidade_Emprestimo {

    private Emprestimo emp = null;
    private Integer id_emp = 1;
    private LocalDate dataHoraEmp = LocalDate.now();
    private LocalDate FimdataHoraEmp = LocalDate.now().plusMonths(1);
    private Utilizador user = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Aveiro, Portugal","121-231-123","ativo");
    private Utilizador userInativo = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Aveiro, Portugal","121-231-123","ativo");
    private EBook eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
    private CopiaEBook copiaEBook = new CopiaEBook(1,eBook);
    private ReplicaProximaUser server = new ReplicaProximaUser();
    private ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro","Portugal");
    private Utilizador user_desativo = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Aveiro, Portugal","121-231-123","desativo");

    public TestUnidade_Emprestimo() throws InvalidUserException, InvalidCopiaEBookException, InvalidReplicaException, InvalidEBookException {
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
        ReplicaProximaUser replicaproximaUser = new ReplicaProximaUser();
        ReplicaServidor replicaServidor_guimaraes = new ReplicaServidor("Guimaraes", "Portugal");
        ReplicaServidor replicaServidor_coimbra = new ReplicaServidor("Coimbra", "Portugal");
        replicaproximaUser.addReplica(replicaServidor_aveiro);
        replicaproximaUser.addReplica(replicaServidor_guimaraes);
        replicaproximaUser.addReplica(replicaServidor_coimbra);

        ReplicaServidor replica = replicaproximaUser.get_Replica_Proxima_Cliente(user);
        user = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Aveiro, Portugal","121-231-123","ativo");

        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook ,1);
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
    void test_BlackBox_Particionamento_Extensao_de_Emprestimo() throws ExtensaoEmprestimoException, EmprestimoException {

        Emprestimo empAtivo = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, eBook,1);
        LocalDate test = empAtivo.getFimdataHoraEmp();
        empAtivo.extenderEmprestimo();

        //Ver se aumentou a primeira extensão e se a data atualizou
        assertEquals(1, empAtivo.getExtensaoEmprestimo());
        assertEquals(test.plusMonths(1), empAtivo.getFimdataHoraEmp());

        empAtivo.extenderEmprestimo();

        //Ver se aumentou a segunda extensão e se a data atualizou
        assertEquals(2,empAtivo.getExtensaoEmprestimo() );
        assertEquals(test.plusMonths(2), empAtivo.getFimdataHoraEmp());

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
