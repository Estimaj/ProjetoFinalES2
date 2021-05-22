import Aplicacao.*;
import Aplicacao.Exceptions.EmprestimoException;
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


    @Test
    void test_Server_sem_replicas_null(){
        ArrayList<ReplicaServidor> aux = new ArrayList<>();
        assertEquals(aux,main_server.getreplicasArrayList());
    }

    @Test
    void test_Server_check_size_0(){
        assertEquals(0,main_server.get_replicas_ArrayList_Size());
    }

    @Test
    void test_Server_get_element_from_array_of_replicas_that_doesnt_exist(){
        ReplicaServidor replica_q_nao_existe = new ReplicaServidor("Evora",copiaEBook);
        assertNull(main_server.getReplicabyElement(replica_q_nao_existe));
    }


    @Test
    void test_Server_get_copia_from_array_of_replicas_that_exist(){
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        assertNotNull(main_server.getCopia_of_Replica(copiaEBook));
    }

    @Test
    void test_Server_get_copia_from_array_of_replicas_that_exist_check_methods(){
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        assertEquals(1,main_server.getCopia_of_Replica(copiaEBook).getId());
        assertEquals(eBook,main_server.getCopia_of_Replica(copiaEBook).getEBook());
    }

    @Test
    void test_Server_get_copia_from_array_of_replicas_that_exist_check_methods_of_EBook(){
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        assertEquals(1,main_server.getCopia_of_Replica(copiaEBook).getId());
        assertEquals("akjshdahq123123",main_server.getCopia_of_Replica(copiaEBook).getEBook().getISBN());
        assertEquals("Stephen King",main_server.getCopia_of_Replica(copiaEBook).getEBook().getAutor());
        assertEquals("The Shinning",main_server.getCopia_of_Replica(copiaEBook).getEBook().getTitulo());
        assertEquals("Ray Lovejoy",main_server.getCopia_of_Replica(copiaEBook).getEBook().getEditora());
        assertEquals("pdf",main_server.getCopia_of_Replica(copiaEBook).getEBook().getFormato());
        assertEquals(150.f,main_server.getCopia_of_Replica(copiaEBook).getEBook().getFileSize());
        assertEquals("Stephen king sig",main_server.getCopia_of_Replica(copiaEBook).getEBook().getSignature());
    }

    @Test
    void test_Server_get_copia_from_array_of_replicas_that_doesnt_exist(){
        CopiaEBook copiaEBook_q_nao_existe = new CopiaEBook(999,eBook);
        assertNull(main_server.getCopia_of_Replica(copiaEBook_q_nao_existe));
    }

    @Test
    void test_devolver_replica_por_id_que_existe(){
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        assertEquals(replicaServidor_aveiro,main_server.get_ReplicaServidor_by_id(0));
    }
    @Test
    void test_devolver_replica_por_id_que_excede_em_demasia_os_limites_do_array(){
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            main_server.get_ReplicaServidor_by_id(1);
        });
        assertEquals(IndexOutOfBoundsException.class, exception.getClass());
    }

    @Test
    void test_devolver_replica_por_id_que_e_inferior_aos_limites_do_array(){
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            main_server.get_ReplicaServidor_by_id(-1);
        });
        assertEquals(IndexOutOfBoundsException.class, exception.getClass());
    }

    @Test
    void test_output_of_Replica_info_String(){
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        String to_Compare = "Aveiro, The Shinning";
        assertEquals(to_Compare,main_server.show_info_replicas());
    }

    @Test
    void test_output_of_Replica_info_String_Null(){
        assertNull(main_server.show_info_replicas());
    }

    @Test
    void test_verifica_se_devolve_replica_mais_proxima_do_cliente_quando_o_array_tem_1_replica() throws EmprestimoException {
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro, Portugal",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        Utilizador u = new Utilizador(1,"Clark","Clark@exemplo.pt","Aveiro, Portugal","111","ativo");
        emp = new Emprestimo(1,LocalDate.now(),LocalDate.now().plusMonths(1),u, copiaEBook, main_server.get_Replica_Proxima_Cliente(u),1);

        assertEquals(replicaServidor_aveiro,main_server.get_Replica_Proxima_Cliente(u));

    }

    @Test
    void test_verifica_se_devolve_replica_mais_proxima_do_cliente_quando_o_array_nao_tem_replicas() throws EmprestimoException {
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro, Portugal",copiaEBook);
        Utilizador u = new Utilizador(1,"Clark","Clark@exemplo.pt","Aveiro, Portugal","111","ativo");
        emp = new Emprestimo(1,LocalDate.now(),LocalDate.now().plusMonths(1),u, copiaEBook, main_server.get_Replica_Proxima_Cliente(u),1);
        assertNull(main_server.get_Replica_Proxima_Cliente(u));

    }

    @Test
    void test_verifica_se_devolve_replica_mais_proxima_do_cliente_quando_o_array_tem_n_replicas() throws EmprestimoException {
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro, Portugal",copiaEBook);
        ReplicaServidor replicaServidor_guimaraes = new ReplicaServidor("Guimaraes, Portugal",copiaEBook);
        ReplicaServidor replicaServidor_coimbra = new ReplicaServidor("Coimbra, Portugal",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        main_server.addReplica(replicaServidor_guimaraes);
        main_server.addReplica(replicaServidor_coimbra);

        Utilizador u = new Utilizador(1,"Clark","Clark@exemplo.pt","Guimaraes, Portugal","111","ativo");
        emp = new Emprestimo(1,LocalDate.now(),LocalDate.now().plusMonths(1),u, copiaEBook, main_server.get_Replica_Proxima_Cliente(u),1);
        assertEquals(replicaServidor_guimaraes,main_server.get_Replica_Proxima_Cliente(u));

    }

    @Test
    void test_verifica_se_devolve_replica_mais_proxima_do_cliente_quando_o_cliente_nao_tem_morada_igual_as_replicas() throws EmprestimoException {
        ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro, Portugal",copiaEBook);
        ReplicaServidor replicaServidor_guimaraes = new ReplicaServidor("Guimaraes, Portugal",copiaEBook);
        ReplicaServidor replicaServidor_coimbra = new ReplicaServidor("Coimbra, Portugal",copiaEBook);
        main_server.addReplica(replicaServidor_aveiro);
        main_server.addReplica(replicaServidor_guimaraes);
        main_server.addReplica(replicaServidor_coimbra);

        Utilizador u = new Utilizador(1,"Clark","Clark@exemplo.pt","Faro, Portugal","111","ativo");
        emp = new Emprestimo(1,LocalDate.now(),LocalDate.now().plusMonths(1),u, copiaEBook, main_server.get_Replica_Proxima_Cliente(u),1);
        assertEquals(replicaServidor_coimbra,main_server.get_Replica_Proxima_Cliente(u));

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
