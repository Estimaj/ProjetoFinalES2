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
    private Utilizador user = new Utilizador(1, "Clark", "clark@exemplo.com", "Abc1abcABC", "Portugal", "121-231-123", "ativo");
    private EBook eBook = new EBook(1, "akjshdahq123123", "Stephen King", "The Shinning", "Ray Lovejoy", "pdf", 150.f, "Stephen king sig");
    private CopiaEBook copiaEBook = new CopiaEBook(1, eBook);
    private GestorReplicas gestorReplicas = new GestorReplicas();
    private ReplicaServidor replicaServidor_portugal = new ReplicaServidor(1,  "Portugal");
    private Utilizador user_desativo = new Utilizador(1, "Clark", "Clark@exemplo.pt", "Abc1abcABC", "Portugal", "121-231-123", "desativado");

    public TestUnidade_Emprestimo() throws InvalidUserException, InvalidCopiaEBookException, InvalidReplicaException, InvalidEBookException, InvalidEBookSizeException, InvalidEBookFormatException {
    }

    @Test
    void CreateEmprestimoOK() throws EmprestimoException {
        emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        assertEquals(1, emp.getId_emp());
    }

    @Test
    void CreateEmprestimoWithUserDesativo() throws InvalidUserException {
        user = new Utilizador(1, "Clark", "clark@exemplo.com", "Abc1abcABC", "Portugal", "121-231-123", "desativado");
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        });
    }

    @Test
    void CreateEmprestimoWithNullParams() {
        assertThrows(NullPointerException.class, () -> {
            emp = new Emprestimo(1, null, null, null, null, 1);
        });
    }

    @Test
    void CreateEmprestimoSetIdEmp() throws EmprestimoException {
        emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        assertEquals(id_emp, emp.getId_emp());
    }

    @Test
    void CreateEmprestimoSetDataHoraEmp() throws EmprestimoException {
        emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        assertEquals(dataHoraEmp, emp.getDataHoraEmp());
    }

    @Test
    void CreateEmprestimoSetFimDataHoraEmp() throws EmprestimoException {
        emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        assertEquals(FimdataHoraEmp, emp.getFimdataHoraEmp());
    }

    @Test
    void createEmprestimoInitialDateEqualstoFinalDate() {
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(id_emp, LocalDate.now(), LocalDate.now(), user, eBook, 1);
        });
    }

    @Test
    void createEmprestimoInitialDateisBeforetoFinalDate() {
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(id_emp, LocalDate.now(), LocalDate.now().minusMonths(1), user, eBook, 1);
        });
    }

    @Test
    void createEmprestimoInitialDateisBetweenDataAtualandFinalDate() throws EmprestimoException {
        emp = new Emprestimo(id_emp, LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(2), user, eBook, 1);
        LocalDate data = LocalDate.now().plusMonths(1);
        assertEquals(data,emp.getDataHoraEmp());
    }


    @Test
    void createEmprestimoInitialDateisBeforeDateAtual() {
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(id_emp, LocalDate.now().minusMonths(1), LocalDate.now().plusMonths(1), user, eBook, 1);
        });
    }

    @Test
    void CreateEmprestimoTROK() throws EmprestimoException {
        emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        assertEquals(1, emp.getAssinaturaTR());
    }

    @Test
    void CreateEmprestimoTRWrong() {
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 0);
        });
    }

    @Test
    void CreateEmprestimoTRDifferent1() {
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 2);
        });
    }

    @Test
    void CreateEmprestimoWithEBook() throws EmprestimoException {
        emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        assertEquals("The Shinning", emp.getEBook().getTitulo());
    }

    @Test
    void CreateEmprestimoWithEBookNull() {
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, null, 1);
        });
    }

    @Test
    void CreateEmprestimoWithCopiaEBook() throws EmprestimoException {
        emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        emp.setCopiaEBook(copiaEBook);
        assertEquals(1, emp.getCopiaEBook().getId());
    }

    @Test
    void CreateEmprestimoWithCopiaEBookNull() throws EmprestimoException {
        emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        assertThrows(EmprestimoException.class, () -> {
            emp.setCopiaEBook(null);
        });
    }

    @Test
    void CreateEmprestimoCheckIdOk() throws EmprestimoException {
        emp = new Emprestimo(1, LocalDate.now(), LocalDate.now().plusMonths(1), user, eBook, 1);
        assertEquals(1, emp.getId_emp());
    }

    @Test
    void CreateEmprestimoCheckIdBelow0() {
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(-1, LocalDate.now(), LocalDate.now().plusMonths(1), user_desativo, eBook, 1);
        });
    }

    @Test
    void CreateEmprestimoCheckIdEqual0() {
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(0, LocalDate.now(), LocalDate.now().plusMonths(1), user_desativo, eBook, 1);
        });

    }

    @Test
    void CreateEmprestimoCheckIdAbove30000() {
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(40000000, LocalDate.now(), LocalDate.now().plusMonths(1), user_desativo, eBook, 1);
        });
    }

    @Test
    void CreateEmprestimoWithReplica() throws EmprestimoException, InvalidReplicaException, InvalidUserException {
        replicaServidor_portugal = new ReplicaServidor(1,"Portugal");
        replicaServidor_portugal.addCopiaEBook(copiaEBook);
        ReplicaServidor replicaServidor_franca = new ReplicaServidor(2, "Franca");
        replicaServidor_franca.addCopiaEBook(copiaEBook);
        ReplicaServidor replicaServidor_Espanha = new ReplicaServidor(3, "Espanha");
        replicaServidor_Espanha.addCopiaEBook(copiaEBook);

        gestorReplicas.addReplica(replicaServidor_portugal);
        gestorReplicas.addReplica(replicaServidor_franca);
        gestorReplicas.addReplica(replicaServidor_Espanha);

        user = new Utilizador(1, "Clark", "clark@exemplo.com", "Abc1abcABC", "Portugal", "121-231-123", "ativo");
        emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        ReplicaServidor replica = gestorReplicas.get_Replica_Proxima_Cliente(emp);

        emp.setReplicaServidor(replica);
        assertEquals("Portugal", emp.getReplicaServidor().getLocalizacaoReplica());
    }

    @Test
    void CreateEmprestimoWithReplicaNull() throws EmprestimoException {
        emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        assertThrows(EmprestimoException.class, () -> {
            emp.setReplicaServidor(null);
        });
    }


    @Test
    void test_setextensaoEmprestimo() throws ExtensaoEmprestimoException, EmprestimoException {
        emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        emp.extenderEmprestimo();
        assertEquals(1, emp.getExtensaoEmprestimo());
        LocalDate extensaoEmprestimo = this.FimdataHoraEmp.plusMonths(1);
        assertEquals(extensaoEmprestimo, emp.getFimdataHoraEmp());
    }

    @Test
    void test_setextensaoEmprestimo_2_vezes() throws ExtensaoEmprestimoException, EmprestimoException {
        emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        emp.extenderEmprestimo();
        emp.extenderEmprestimo();
        assertEquals(2, emp.getExtensaoEmprestimo());
        LocalDate extensaoEmprestimo = this.FimdataHoraEmp.plusMonths(2);
        assertEquals(extensaoEmprestimo, emp.getFimdataHoraEmp());
    }

    @Test
    void test_setextensaoEmprestimo_Exception() throws ExtensaoEmprestimoException, EmprestimoException {
        emp = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        emp.extenderEmprestimo();
        assertEquals(1, emp.getExtensaoEmprestimo());
    }


    @Test
    void Extensao_de_Emprestimo_1_Vez() throws ExtensaoEmprestimoException, EmprestimoException {
        Emprestimo empAtivo = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        LocalDate test = empAtivo.getFimdataHoraEmp();
        empAtivo.extenderEmprestimo();

        //Ver se aumentou a primeira extensão e se a data atualizou
        assertEquals(1, empAtivo.getExtensaoEmprestimo());
        assertEquals(test.plusMonths(1), empAtivo.getFimdataHoraEmp());

    }

    @Test
    void Extensao_de_Emprestimo_2_Vez() throws ExtensaoEmprestimoException, EmprestimoException {
        Emprestimo empAtivo = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        LocalDate test = empAtivo.getFimdataHoraEmp();
        empAtivo.extenderEmprestimo();

        empAtivo.extenderEmprestimo();

        //Ver se aumentou a segunda extensão e se a data atualizou
        assertEquals(2, empAtivo.getExtensaoEmprestimo());
        assertEquals(test.plusMonths(2), empAtivo.getFimdataHoraEmp());


    }

    @Test
    void Extensao_de_Emprestimo_3_Vez() throws ExtensaoEmprestimoException, EmprestimoException {
        Emprestimo empAtivo = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);
        LocalDate test = empAtivo.getFimdataHoraEmp();
        empAtivo.extenderEmprestimo();


        empAtivo.extenderEmprestimo();


        //Atirar throw à terceira extensão
        assertThrows(ExtensaoEmprestimoException.class, () -> {
            empAtivo.extenderEmprestimo();
        });
    }

    @Test
    void UpdateEmprestimoSetFimdataHoraEmpEqualdataHoraEmprestimo() throws EmprestimoException {
        Emprestimo empAtivo = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);

        assertThrows(EmprestimoException.class, () -> {
            empAtivo.setFimdataHoraEmp(dataHoraEmp);
        });
    }

    @Test
    void UpdateEmprestimoSetFimdataHoraEmpBeforedataHoraEmprestimo() throws EmprestimoException {
        Emprestimo empAtivo = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);

        assertThrows(EmprestimoException.class, () -> {
            empAtivo.setFimdataHoraEmp(dataHoraEmp.minusMonths(1));
        });
    }

    @Test
    void UpdateEmprestimoSetUtilizadorDesativado() throws EmprestimoException {
        Emprestimo empAtivo = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);

        assertThrows(EmprestimoException.class, () -> {
            empAtivo.setUtilizador(user_desativo);
        });
    }

    @Test
    void UpdateEmprestimoSetEBookNull() throws EmprestimoException {
        Emprestimo empAtivo = new Emprestimo(id_emp, dataHoraEmp, FimdataHoraEmp, user, eBook, 1);

        assertThrows(EmprestimoException.class, () -> {
            empAtivo.setEBook(null);
        });
    }

    @Test
    void CreateEmprestimoWithCanceledAccount() {
        //so apanha quando o user esta cancelado
        System.out.println("test_Emprestimo_Conta_Cancelada ==> " + user_desativo.getEstado_utilizador());
        replicaServidor_portugal.addCopiaEBook(copiaEBook);
        gestorReplicas.addReplica(replicaServidor_portugal);
        assertThrows(EmprestimoException.class, () -> {
            emp = new Emprestimo(1, LocalDate.now(), LocalDate.now().plusMonths(1), user_desativo, eBook, 1);
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
