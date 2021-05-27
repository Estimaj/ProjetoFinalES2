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

    private Emprestimo emp = null;
    private EBook eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
    private CopiaEBook copiaEBook = new CopiaEBook(1,eBook);
    private Server main_server = new Server("Portugal");
    private ReplicaServidor replicaServidor_aveiro = null;

    public TestUnidade_ReplicaServidor() throws InvalidCopiaEBookException, InvalidServerException, InvalidEBookException {
    }

    @Test
    void createReplicaOK() throws InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        assertNotNull(replicaServidor_aveiro);
    }

    @Test
    void createReplicaInvalidLocalizacaoNumber() {
        assertThrows(InvalidReplicaException.class, () -> {
            replicaServidor_aveiro = new ReplicaServidor("1",copiaEBook);
        });
    }

    @Test
    void createReplicaInvalidLocalizacaoNull() {
        assertThrows(InvalidReplicaException.class, () -> {
            replicaServidor_aveiro = new ReplicaServidor(null,copiaEBook);
        });
    }

    @Test
    void createReplicaInvalidLocalizacaoEmpty() {
        assertThrows(InvalidReplicaException.class, () -> {
            replicaServidor_aveiro = new ReplicaServidor("",copiaEBook);
        });
    }


    @Test
    void test_Server_sem_replicas_null(){
        ArrayList<ReplicaServidor> aux = new ArrayList<>();
        assertEquals(aux,main_server.getreplicasArrayList());
    }

    @Test
    void getReplicaThatDoesNotExist() throws InvalidReplicaException {
        ReplicaServidor replica_q_nao_existe = new ReplicaServidor("Evora",copiaEBook);
        assertNull(main_server.getReplicabyElement(replica_q_nao_existe));
    }


    @Test
    void getCopiaEBookofSpecificReplicaOK() throws InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);

        assertNotNull(main_server.getCopia_of_Replica1(replicaServidor_aveiro));
    }

    @Test
    void getReplicaThatExisttoCheckMethodsofCopiaEBook() throws InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        assertEquals(1,main_server.getCopia_of_Replica(copiaEBook).getId());
        assertEquals(eBook,main_server.getCopia_of_Replica(copiaEBook).getEBook());
    }

    @Test
    void getReplicaThatExisttoCheckMethodsofCopiaEBook_in_EBook() throws InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        assertEquals(1,main_server.getCopia_of_Replica(copiaEBook).getId());
        assertEquals("akjshdahq123123",main_server.getCopia_of_Replica(copiaEBook).getEBook().getISBN());
        assertEquals("Stephen King",main_server.getCopia_of_Replica(copiaEBook).getEBook().getAutor());
        assertEquals("The Shinning",main_server.getCopia_of_Replica(copiaEBook).getEBook().getTitulo());
        assertEquals("Ray Lovejoy",main_server.getCopia_of_Replica(copiaEBook).getEBook().getEditora());
        assertEquals("pdf",main_server.getCopia_of_Replica(copiaEBook).getEBook().getFormato());
        assertEquals(150.f,main_server.getCopia_of_Replica(copiaEBook).getEBook().getFileSize());
        assertEquals("Stephen king sig",main_server.getCopia_of_Replica(copiaEBook).getEBook().getHash());
    }

    @Test
    void getNullCopiaEBookofReplica() throws InvalidCopiaEBookException {
        CopiaEBook copiaEBook_q_nao_existe = new CopiaEBook(999,eBook);
        assertNull(main_server.getCopia_of_Replica(copiaEBook_q_nao_existe));
    }

    @Test
    void getSpecificReplicabyId() throws InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        assertEquals(replicaServidor_aveiro,main_server.get_ReplicaServidor_by_id(0));
    }
    @Test
    void getReplicaThatExceedsArrayIndexBounds() throws InvalidReplicaException {
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            main_server.get_ReplicaServidor_by_id(1);
        });
        assertEquals(IndexOutOfBoundsException.class, exception.getClass());
    }

    @Test
    void getReplicaThatisLessofArrayIndexBounds() throws InvalidReplicaException {
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            main_server.get_ReplicaServidor_by_id(-1);
        });
        assertEquals(IndexOutOfBoundsException.class, exception.getClass());
    }

    @Test
    void CheckContentofReplicasCopiaEBook() throws InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        String to_Compare = "Aveiro, The Shinning";
        assertEquals(to_Compare,main_server.show_info_replicas());
    }

    @Test
    void CheckNullContentofReplicasCopiaEBook(){
        assertNull(main_server.show_info_replicas());
    }

    @Test
    void getReplicaWithOnlyOneReplicaAvaliable() throws EmprestimoException, InvalidUserException, InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro, Portugal",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        Utilizador u = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Aveiro, Portugal","111","ativo");
        emp = new Emprestimo(1,LocalDate.now(),LocalDate.now().plusMonths(1),u, copiaEBook, main_server.get_Replica_Proxima_Cliente(u),1);

        assertEquals(replicaServidor_aveiro,main_server.get_Replica_Proxima_Cliente(u));

    }

    @Test
    void getReplicaWithNoReplicasAvaliable() throws EmprestimoException, InvalidUserException, InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro, Portugal",copiaEBook);
        Utilizador u = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Aveiro, Portugal","111","ativo");
        emp = new Emprestimo(1,LocalDate.now(),LocalDate.now().plusMonths(1),u, copiaEBook, main_server.get_Replica_Proxima_Cliente(u),1);
        assertNull(main_server.get_Replica_Proxima_Cliente(u));

    }

    @Test
    void getReplicaClosetoUserWithReplicasAvaliable() throws EmprestimoException, InvalidUserException, InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro, Portugal",copiaEBook);
        ReplicaServidor replicaServidor_guimaraes = new ReplicaServidor("Guimaraes, Portugal",copiaEBook);
        ReplicaServidor replicaServidor_coimbra = new ReplicaServidor("Coimbra, Portugal",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        main_server.addReplica(replicaServidor_guimaraes);
        main_server.addReplica(replicaServidor_coimbra);

        Utilizador u = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Guimaraes, Portugal","111","ativo");
        emp = new Emprestimo(1,LocalDate.now(),LocalDate.now().plusMonths(1),u, copiaEBook, main_server.get_Replica_Proxima_Cliente(u),1);
        assertEquals(replicaServidor_guimaraes,main_server.get_Replica_Proxima_Cliente(u));

    }

    @Test
    void getReplicaClosetoUserWithReplicasAvaliablebutisntsameCityasUser() throws EmprestimoException, InvalidUserException, InvalidReplicaException {
        replicaServidor_aveiro = new ReplicaServidor("Aveiro, Portugal",copiaEBook);
        ReplicaServidor replicaServidor_guimaraes = new ReplicaServidor("Guimaraes, Portugal",copiaEBook);
        ReplicaServidor replicaServidor_coimbra = new ReplicaServidor("Coimbra, Portugal",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        main_server.addReplica(replicaServidor_guimaraes);
        main_server.addReplica(replicaServidor_coimbra);

        Utilizador u = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Faro, Portugal","111","ativo");
        emp = new Emprestimo(1,LocalDate.now(),LocalDate.now().plusMonths(1),u, copiaEBook, main_server.get_Replica_Proxima_Cliente(u),1);
        assertEquals(replicaServidor_coimbra,main_server.get_Replica_Proxima_Cliente(u));

    }

    @Test
    void getReplicasSizeWith0replicas(){
        assertEquals(0,main_server.get_replicas_ArrayList_Size());
    }

    @Test
    void getReplicasSizeWithNreplicas() throws InvalidReplicaException {
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        assertEquals(1,main_server.get_replicas_ArrayList_Size());
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
