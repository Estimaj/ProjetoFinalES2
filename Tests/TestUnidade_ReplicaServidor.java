import Aplicacao.*;
import Aplicacao.Exceptions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestUnidade_ReplicaServidor {

    private Utilizador u = new Utilizador(1, "Clark", "clark@exemplo.com", "Abc1abcABC", "Portugal", "121-231-123", "ativo");
    private EBook eBook = new EBook(1, "akjshdahq123123", "Stephen King", "The Shinning", "Ray Lovejoy", "pdf", 150.f, "Stephen king sig");
    private CopiaEBook copiaEBook = new CopiaEBook(1, eBook);
    private GestorReplicas gestorReplicas = new GestorReplicas();
    private ReplicaServidor replicaServidor_portugal = null;
    private Emprestimo emp = new Emprestimo(1, LocalDate.now(), LocalDate.now().plusMonths(1), u, eBook, 1);
    ;

    public TestUnidade_ReplicaServidor() throws InvalidCopiaEBookException, InvalidEBookException, InvalidUserException, EmprestimoException, InvalidEBookSizeException, InvalidEBookFormatException {
    }

    @Test
    void createReplicaOK() throws InvalidReplicaException {
        replicaServidor_portugal = new ReplicaServidor(1, "Portugal");
        assertNotNull(replicaServidor_portugal);
    }

    @Test
    void createReplicaIdOk() throws InvalidReplicaException {
        replicaServidor_portugal = new ReplicaServidor(1, "Portugal");
        assertEquals(1, replicaServidor_portugal.getId_replica());
    }

    @Test
    void createReplicaIdLessThenMinimum() {
        assertThrows(InvalidReplicaException.class, () -> {
            replicaServidor_portugal = new ReplicaServidor(-1, "Portugal");
        });
    }

    @Test
    void createReplicaIdEqualsTo0() {
        assertThrows(InvalidReplicaException.class, () -> {
            replicaServidor_portugal = new ReplicaServidor(0, "Portugal");
        });
    }

    @Test
    void createReplicaIdExcedsMaximum() {
        assertThrows(InvalidReplicaException.class, () -> {
            replicaServidor_portugal = new ReplicaServidor(40000, "Portugal");
        });
    }

    @Test
    void createReplicaIdEqualsMaximum() {
        assertThrows(InvalidReplicaException.class, () -> {
            replicaServidor_portugal = new ReplicaServidor(30001, "Portugal");
        });
    }

    @Test
    void createReplicaIdLessMaximum() throws InvalidReplicaException {
        replicaServidor_portugal = new ReplicaServidor(30000, "Portugal");
        assertEquals(30000, replicaServidor_portugal.getId_replica());
    }

    @Test
    void createReplicaInvalidLocalizacaoNumber() {
        assertThrows(InvalidReplicaException.class, () -> {
            replicaServidor_portugal = new ReplicaServidor(1, "01010");
        });
    }

    @Test
    void createReplicaInvalidLocalizacaoNull() {
        assertThrows(InvalidReplicaException.class, () -> {
            replicaServidor_portugal = new ReplicaServidor(1, null);
        });
    }

    @Test
    void createReplicaInvalidLocalizacaoEmpty() {
        assertThrows(InvalidReplicaException.class, () -> {
            replicaServidor_portugal = new ReplicaServidor(1, "");
        });
    }


    @Test
    void test_Server_sem_replicas_null() {
        ArrayList<ReplicaServidor> aux = new ArrayList<>();
        assertEquals(aux, gestorReplicas.getreplicasArrayList());
    }

    @Test
    void getReplicaThatDoesNotExist() throws InvalidReplicaException {
        ReplicaServidor replica_q_nao_existe = new ReplicaServidor(1, "Monaco");
        assertNull(gestorReplicas.getReplicabyElement(replica_q_nao_existe));
    }


    @Test
    void getCopiaEBookofSpecificReplicaOK() throws InvalidReplicaException {
        replicaServidor_portugal = new ReplicaServidor(1, "Portugal");
        gestorReplicas.addReplica(replicaServidor_portugal);
        assertNotNull(gestorReplicas.getReplicabyElement(replicaServidor_portugal));
    }

    @Test
    void getReplicaThatExisttoCheckMethodsofCopiaEBook() throws InvalidReplicaException {
        replicaServidor_portugal = new ReplicaServidor(1, "Portugal");
        replicaServidor_portugal.addCopiaEBook(copiaEBook);
        gestorReplicas.addReplica(replicaServidor_portugal);
        assertEquals(1, gestorReplicas.getCopia_of_Replica(copiaEBook).getId());
        assertEquals(eBook, gestorReplicas.getCopia_of_Replica(copiaEBook).getEBook());
    }

    @Test
    void getReplicaThatExisttoCheckMethodsofCopiaEBook_in_EBook() throws InvalidReplicaException {
        replicaServidor_portugal = new ReplicaServidor(1, "Portugal");
        replicaServidor_portugal.addCopiaEBook(copiaEBook);
        gestorReplicas.addReplica(replicaServidor_portugal);
        assertEquals(1, gestorReplicas.getCopia_of_Replica(copiaEBook).getId());
        assertEquals("akjshdahq123123", gestorReplicas.getCopia_of_Replica(copiaEBook).getEBook().getISBN());
        assertEquals("Stephen King", gestorReplicas.getCopia_of_Replica(copiaEBook).getEBook().getAutor());
        assertEquals("The Shinning", gestorReplicas.getCopia_of_Replica(copiaEBook).getEBook().getTitulo());
        assertEquals("Ray Lovejoy", gestorReplicas.getCopia_of_Replica(copiaEBook).getEBook().getEditora());
        assertEquals("pdf", gestorReplicas.getCopia_of_Replica(copiaEBook).getEBook().getFormato());
        assertEquals(150.f, gestorReplicas.getCopia_of_Replica(copiaEBook).getEBook().getFileSize());
        assertEquals("Bd5m2KhLtmXkK90GcrQIov5mYKAj5mL7u8rxB+zYHvQ=", gestorReplicas.getCopia_of_Replica(copiaEBook).getEBook().getHash());
    }

    @Test
    void getNullCopiaEBookofReplica() throws InvalidCopiaEBookException {
        CopiaEBook copiaEBook_q_nao_existe = new CopiaEBook(999, eBook);
        gestorReplicas.addReplica(replicaServidor_portugal);
        assertThrows(NullPointerException.class, () -> {
            assertNull(gestorReplicas.getCopia_of_Replica(copiaEBook_q_nao_existe));
        });
    }

    @Test
    void getSpecificReplicabyId() throws InvalidReplicaException {
        replicaServidor_portugal = new ReplicaServidor(1, "Portugal");
        gestorReplicas.addReplica(replicaServidor_portugal);
        assertEquals(replicaServidor_portugal, gestorReplicas.get_ReplicaServidor_by_id(0));
    }

    @Test
    void getReplicaThatExceedsArrayIndexBounds() throws InvalidReplicaException {
        replicaServidor_portugal = new ReplicaServidor(1, "Portugal");
        gestorReplicas.addReplica(replicaServidor_portugal);
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            gestorReplicas.get_ReplicaServidor_by_id(1);
        });
        assertEquals(IndexOutOfBoundsException.class, exception.getClass());
    }

    @Test
    void getReplicaThatisLessofArrayIndexBounds() throws InvalidReplicaException {
        replicaServidor_portugal = new ReplicaServidor(1, "Portugal");
        gestorReplicas.addReplica(replicaServidor_portugal);
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            gestorReplicas.get_ReplicaServidor_by_id(-1);
        });
        assertEquals(IndexOutOfBoundsException.class, exception.getClass());
    }

    @Test
    void CheckContentofReplicasCopiaEBook() throws InvalidReplicaException {
        replicaServidor_portugal = new ReplicaServidor(1, "Portugal");
        replicaServidor_portugal.addCopiaEBook(copiaEBook);
        gestorReplicas.addReplica(replicaServidor_portugal);
        String to_Compare = "Portugal,The Shinning";
        assertEquals(to_Compare, gestorReplicas.show_info_replicas());
    }

    @Test
    void CheckNullContentofReplicasCopiaEBook() {
        assertNull(gestorReplicas.show_info_replicas());
    }

    @Test
    void getReplicaWithOnlyOneReplicaAvaliable() throws EmprestimoException, InvalidUserException, InvalidReplicaException {
        replicaServidor_portugal = new ReplicaServidor(1, "Portugal");
        replicaServidor_portugal.addCopiaEBook(copiaEBook);
        gestorReplicas.addReplica(replicaServidor_portugal);
        u = new Utilizador(1, "Clark", "clark@exemplo.com", "Abc1abcABC", "Portugal", "121-231-123", "ativo");


        assertEquals(replicaServidor_portugal, gestorReplicas.get_Replica_Proxima_Cliente(emp));

    }

    @Test
    void getReplicaWithNoReplicasAvaliable() throws InvalidReplicaException, EmprestimoException {
        replicaServidor_portugal = new ReplicaServidor(1, "Portugal");
        replicaServidor_portugal.addCopiaEBook(copiaEBook);

        //devolve null
        assertNull(gestorReplicas.get_Replica_Proxima_Cliente(emp));

    }

    @Test
    void getReplicaWhenUserisNull() throws EmprestimoException, InvalidReplicaException {
        replicaServidor_portugal = new ReplicaServidor(1, "Portugal");
        replicaServidor_portugal.addCopiaEBook(copiaEBook);
        ReplicaServidor replicaServidor_franca = new ReplicaServidor(2, "Franca");
        replicaServidor_franca.addCopiaEBook(copiaEBook);
        ReplicaServidor replicaServidor_Espanha = new ReplicaServidor(3, "Espanha");
        replicaServidor_Espanha.addCopiaEBook(copiaEBook);

        gestorReplicas.addReplica(replicaServidor_portugal);
        gestorReplicas.addReplica(replicaServidor_franca);
        gestorReplicas.addReplica(replicaServidor_Espanha);

        emp = new Emprestimo(1, LocalDate.now(), LocalDate.now().plusMonths(2), u, eBook, 1);
        emp.setReplicaServidor(replicaServidor_franca);


        //devolve a replica existente na cidade do USER
        assertThrows(NullPointerException.class, () -> {
            emp.setUtilizador(null);
        });

        assertEquals(replicaServidor_portugal, gestorReplicas.get_Replica_Proxima_Cliente(emp));

    }

    @Test
    void getReplicaClosetoUserWithReplicasAvaliable() throws EmprestimoException, InvalidUserException, InvalidReplicaException {
        replicaServidor_portugal = new ReplicaServidor(1, "Portugal");
        replicaServidor_portugal.addCopiaEBook(copiaEBook);
        ReplicaServidor replicaServidor_franca = new ReplicaServidor(2, "Franca");
        replicaServidor_franca.addCopiaEBook(copiaEBook);
        ReplicaServidor replicaServidor_Espanha = new ReplicaServidor(3, "Espanha");
        replicaServidor_Espanha.addCopiaEBook(copiaEBook);

        gestorReplicas.addReplica(replicaServidor_portugal);
        gestorReplicas.addReplica(replicaServidor_franca);
        gestorReplicas.addReplica(replicaServidor_Espanha);

        u = new Utilizador(1, "Clark", "clark@exemplo.com", "Abc1abcABC", "Franca", "121-231-123", "ativo");
        emp = new Emprestimo(1, LocalDate.now(), LocalDate.now().plusMonths(2), u, eBook, 1);
        emp.setReplicaServidor(replicaServidor_franca);
        //devolve a replica existente na cidade do USER
        assertEquals(emp.getReplicaServidor(), gestorReplicas.get_Replica_Proxima_Cliente(emp));

    }

    @Test
    void Get_replica_with_different_City_as_the_User() throws EmprestimoException, InvalidUserException, InvalidReplicaException {
        ReplicaServidor replicaServidor_franca = new ReplicaServidor(2, "Franca");
        replicaServidor_franca.addCopiaEBook(copiaEBook);
        ReplicaServidor replicaServidor_Espanha = new ReplicaServidor(3, "Espanha");
        replicaServidor_Espanha.addCopiaEBook(copiaEBook);
        replicaServidor_portugal = new ReplicaServidor(1, "Portugal");
        replicaServidor_portugal.addCopiaEBook(copiaEBook);

        gestorReplicas.addReplica(replicaServidor_franca); //1ª -> vai devolver esta replica
        gestorReplicas.addReplica(replicaServidor_Espanha); //2ª
        gestorReplicas.addReplica(replicaServidor_portugal); //3ª

        u = new Utilizador(1, "Clark", "clark@exemplo.com", "Abc1abcABC", "Inglaterra", "121-231-123", "ativo");
        emp = new Emprestimo(1, LocalDate.now(), LocalDate.now().plusMonths(2), u, eBook, 1);

        System.out.println("-> " + gestorReplicas.get_Replica_Proxima_Cliente(emp).getLocalizacaoReplica());

        //devolve a primeira que encontrar, caso nao exista nenhuma replica na cidade do USER
        assertEquals(replicaServidor_franca, gestorReplicas.get_Replica_Proxima_Cliente(emp));

    }

    @Test
    void getReplicasSizeWith0replicas() {
        assertEquals(0, gestorReplicas.get_replicas_ArrayList_Size());
    }

    @Test
    void getReplicasSizeWithNreplicas() throws InvalidReplicaException {
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor(1, "Portugal");
        gestorReplicas.addReplica(replicaServidor_aveiro);
        assertEquals(1, gestorReplicas.get_replicas_ArrayList_Size());
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
