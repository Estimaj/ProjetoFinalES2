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

    private Utilizador u = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Aveiro, Portugal","121-231-123","ativo");
    private EBook eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
    private CopiaEBook copiaEBook = new CopiaEBook(1,eBook);
    private ReplicaProximaUser replicaproximaUser = new ReplicaProximaUser();
    private ReplicaServidor replicaServidor_aveiro = null;
    private Emprestimo emp = new Emprestimo(1, LocalDate.now(),LocalDate.now().plusMonths(1),u, eBook ,1);;

    public TestUnidade_ReplicaServidor() throws InvalidCopiaEBookException, InvalidServerException, InvalidEBookException, InvalidUserException, EmprestimoException {
    }

    @Test
    void createReplicaOK() throws InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro","Portugal");
        assertNotNull(replicaServidor_aveiro);
    }

    @Test
    void createReplicaInvalidLocalizacaoNumber() {
        assertThrows(InvalidReplicaException.class, () -> {
            replicaServidor_aveiro = new ReplicaServidor("1","1");
        });
    }

    @Test
    void createReplicaInvalidLocalizacaoNull() {
        assertThrows(InvalidReplicaException.class, () -> {
            replicaServidor_aveiro = new ReplicaServidor(null,null);
        });
    }

    @Test
    void createReplicaInvalidLocalizacaoEmpty() {
        assertThrows(InvalidReplicaException.class, () -> {
            replicaServidor_aveiro = new ReplicaServidor("","");
        });
    }


    @Test
    void test_Server_sem_replicas_null(){
        ArrayList<ReplicaServidor> aux = new ArrayList<>();
        assertEquals(aux,replicaproximaUser.getreplicasArrayList());
    }

    @Test
    void getReplicaThatDoesNotExist() throws InvalidReplicaException {
        ReplicaServidor replica_q_nao_existe = new ReplicaServidor("Evora","Portugal");
        assertNull(replicaproximaUser.getReplicabyElement(replica_q_nao_existe));
    }


    @Test
    void getCopiaEBookofSpecificReplicaOK() throws InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro","Portugal");
        replicaproximaUser.addReplica(replicaServidor_aveiro);
        assertNotNull(replicaproximaUser.getReplicabyElement(replicaServidor_aveiro));
    }

    @Test
    void getReplicaThatExisttoCheckMethodsofCopiaEBook() throws InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro","Portugal");
        replicaServidor_aveiro.addCopiaEBook(copiaEBook);
        replicaproximaUser.addReplica(replicaServidor_aveiro);
        assertEquals(1,replicaproximaUser.getCopia_of_Replica(copiaEBook).getId());
        assertEquals(eBook,replicaproximaUser.getCopia_of_Replica(copiaEBook).getEBook());
    }

    @Test
    void getReplicaThatExisttoCheckMethodsofCopiaEBook_in_EBook() throws InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro","Portugal");
        replicaServidor_aveiro.addCopiaEBook(copiaEBook);
        replicaproximaUser.addReplica(replicaServidor_aveiro);
        assertEquals(1,replicaproximaUser.getCopia_of_Replica(copiaEBook).getId());
        assertEquals("akjshdahq123123",replicaproximaUser.getCopia_of_Replica(copiaEBook).getEBook().getISBN());
        assertEquals("Stephen King",replicaproximaUser.getCopia_of_Replica(copiaEBook).getEBook().getAutor());
        assertEquals("The Shinning",replicaproximaUser.getCopia_of_Replica(copiaEBook).getEBook().getTitulo());
        assertEquals("Ray Lovejoy",replicaproximaUser.getCopia_of_Replica(copiaEBook).getEBook().getEditora());
        assertEquals("pdf",replicaproximaUser.getCopia_of_Replica(copiaEBook).getEBook().getFormato());
        assertEquals(150.f,replicaproximaUser.getCopia_of_Replica(copiaEBook).getEBook().getFileSize());
        assertEquals("Bd5m2KhLtmXkK90GcrQIov5mYKAj5mL7u8rxB+zYHvQ=",replicaproximaUser.getCopia_of_Replica(copiaEBook).getEBook().getHash());
    }

    @Test
    void getNullCopiaEBookofReplica() throws InvalidCopiaEBookException {
        CopiaEBook copiaEBook_q_nao_existe = new CopiaEBook(999,eBook);
        replicaproximaUser.addReplica(replicaServidor_aveiro);
        assertThrows(NullPointerException.class, () -> {
            assertNull(replicaproximaUser.getCopia_of_Replica(copiaEBook_q_nao_existe));
        });
    }

    @Test
    void getSpecificReplicabyId() throws InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro","Portugal");
        replicaproximaUser.addReplica(replicaServidor_aveiro);
        assertEquals(replicaServidor_aveiro,replicaproximaUser.get_ReplicaServidor_by_id(0));
    }

    @Test
    void getReplicaThatExceedsArrayIndexBounds() throws InvalidReplicaException {
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro","Portugal");
        replicaproximaUser.addReplica(replicaServidor_aveiro);
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            replicaproximaUser.get_ReplicaServidor_by_id(1);
        });
        assertEquals(IndexOutOfBoundsException.class, exception.getClass());
    }

    @Test
    void getReplicaThatisLessofArrayIndexBounds() throws InvalidReplicaException {
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro","Portugal");
        replicaproximaUser.addReplica(replicaServidor_aveiro);
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            replicaproximaUser.get_ReplicaServidor_by_id(-1);
        });
        assertEquals(IndexOutOfBoundsException.class, exception.getClass());
    }

    @Test
    void CheckContentofReplicasCopiaEBook() throws InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro","Portugal");
        replicaServidor_aveiro.addCopiaEBook(copiaEBook);
        replicaproximaUser.addReplica(replicaServidor_aveiro);
        String to_Compare = "Aveiro,Portugal,The Shinning";
        assertEquals(to_Compare,replicaproximaUser.show_info_replicas());
    }

    @Test
    void CheckNullContentofReplicasCopiaEBook(){
        assertNull(replicaproximaUser.show_info_replicas());
    }

    @Test
    void getReplicaWithOnlyOneReplicaAvaliable() throws EmprestimoException, InvalidUserException, InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro"," Portugal");
        replicaServidor_aveiro.addCopiaEBook(copiaEBook);
        replicaproximaUser.addReplica(replicaServidor_aveiro);
        u = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Guimaraes, Portugal","121-231-123","ativo");


        assertEquals(replicaServidor_aveiro,replicaproximaUser.get_Replica_Proxima_Cliente(emp));

    }

    @Test
    void getReplicaWithNoReplicasAvaliable() throws InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro", "Portugal");
        replicaServidor_aveiro.addCopiaEBook(copiaEBook);

        //devolve null
        assertNull(replicaproximaUser.get_Replica_Proxima_Cliente(emp));

    }

    @Test
    void getReplicaClosetoUserWithReplicasAvaliable() throws EmprestimoException, InvalidUserException, InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro", "Portugal");
        replicaServidor_aveiro.addCopiaEBook(copiaEBook);
        ReplicaServidor replicaServidor_guimaraes = new ReplicaServidor("Guimaraes", "Portugal");
        replicaServidor_guimaraes.addCopiaEBook(copiaEBook);
        ReplicaServidor replicaServidor_coimbra = new ReplicaServidor("Coimbra", "Portugal");
        replicaServidor_coimbra.addCopiaEBook(copiaEBook);

        replicaproximaUser.addReplica(replicaServidor_aveiro);
        replicaproximaUser.addReplica(replicaServidor_guimaraes);
        replicaproximaUser.addReplica(replicaServidor_coimbra);

        u = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Guimaraes, Portugal","121-231-123","ativo");
        emp = new Emprestimo(1,LocalDate.now(), LocalDate.now().plusMonths(2),u,eBook,1);

        //devolve a replica existente na cidade do USER
        assertEquals(replicaServidor_guimaraes,replicaproximaUser.get_Replica_Proxima_Cliente(emp));

    }

    @Test
    void getReplicaClosetoUserWithReplicasAvaliablebutisntsameCityasUser() throws EmprestimoException, InvalidUserException, InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro", "Portugal");
        replicaServidor_aveiro.addCopiaEBook(copiaEBook);
        ReplicaServidor replicaServidor_guimaraes = new ReplicaServidor("Guimaraes", "Portugal");
        replicaServidor_guimaraes.addCopiaEBook(copiaEBook);
        ReplicaServidor replicaServidor_coimbra = new ReplicaServidor("Coimbra", "Portugal");
        replicaServidor_coimbra.addCopiaEBook(copiaEBook);
        replicaproximaUser.addReplica(replicaServidor_aveiro);
        replicaproximaUser.addReplica(replicaServidor_guimaraes);
        replicaproximaUser.addReplica(replicaServidor_coimbra);


        u = new Utilizador(1,"Clark","clark@exemplo.com","Abc1abcABC","Faro, Portugal","121-231-123","ativo");
        emp = new Emprestimo(1,LocalDate.now(), LocalDate.now().plusMonths(2),u,eBook,1);

        //devolve a primeira que encontrar, caso nao exista nenhuma replica na cidade do USER
        assertEquals(replicaServidor_aveiro,replicaproximaUser.get_Replica_Proxima_Cliente(emp));

    }

    @Test
    void getReplicasSizeWith0replicas(){
        assertEquals(0,replicaproximaUser.get_replicas_ArrayList_Size());
    }

    @Test
    void getReplicasSizeWithNreplicas() throws InvalidReplicaException {
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro","Portugal");
        replicaproximaUser.addReplica(replicaServidor_aveiro);
        assertEquals(1,replicaproximaUser.get_replicas_ArrayList_Size());
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
