import Exceptions.ExtensaoEmprestimoException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestUnidade_Emprestimo {

    private Emprestimo emp = null;
    private Integer id_emp = null;
    private LocalDate dataHoraEmp = null;
    private LocalDate FimdataHoraEmp = null;
    private Utilizador user = new Utilizador(1,"Clark","Clark@exemplo.pt","Krypton","111","ativo");
    private EBook eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
    private CopiaEBook copiaEBook = new CopiaEBook(1,eBook);
    private ReplicaServidor replicaServidor = new ReplicaServidor("Viseu");

    @Test
    void test_Criacao_Emprestimo(){
        id_emp = 1;
        dataHoraEmp = LocalDate.now();
        FimdataHoraEmp = dataHoraEmp;
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, copiaEBook,replicaServidor);
        assertNotNull(emp);
    }

    @Test
    void test_Criacao_Emprestimo_com_null_params(){
        emp = new Emprestimo(0,null,null,null,null,null);
        assertEquals(0,emp.getId_emp());
        assertNull(emp.getDataHoraEmp());
        assertNull(emp.getFimdataHoraEmp());
        assertNull(emp.getUtilizador());
        assertNull(emp.getCopiaEBook());
        assertNull(emp.getReplicaServidor());
    }

    @Test
    void test_setIdEmprestimo(){
        id_emp = 2;
        dataHoraEmp = LocalDate.now();
        FimdataHoraEmp = dataHoraEmp;
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, copiaEBook,replicaServidor);
        assertEquals(id_emp,emp.getId_emp());
    }

    @Test
    void test_setDataHoraEmp(){
        id_emp = 2;
        dataHoraEmp = LocalDate.now();
        FimdataHoraEmp = dataHoraEmp;
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, copiaEBook,replicaServidor);
        assertEquals(dataHoraEmp,emp.getDataHoraEmp());
    }

    @Test
    void test_setFimdataHoraEmp(){
        id_emp = 2;
        dataHoraEmp = LocalDate.now();
        FimdataHoraEmp = dataHoraEmp;
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, copiaEBook,replicaServidor);
        assertEquals(FimdataHoraEmp,emp.getFimdataHoraEmp());
    }

    @Test
    void test_setextensaoEmprestimo() throws ExtensaoEmprestimoException {
        id_emp = 2;
        dataHoraEmp = LocalDate.now();
        FimdataHoraEmp = dataHoraEmp;
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, copiaEBook,replicaServidor);
        emp.extenderEmprestimo();
        assertEquals(1,emp.getExtensaoEmprestimo());
    }
    @Test
    void test_setextensaoEmprestimo_2_vezes() throws ExtensaoEmprestimoException {
        id_emp = 2;
        dataHoraEmp = LocalDate.now();
        FimdataHoraEmp = dataHoraEmp;
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, copiaEBook,replicaServidor);
        emp.extenderEmprestimo();
        emp.extenderEmprestimo();
        assertEquals(2,emp.getExtensaoEmprestimo());
    }

    @Test
    void test_setextensaoEmprestimo_Exception() throws ExtensaoEmprestimoException {
        id_emp = 2;
        dataHoraEmp = LocalDate.now();
        FimdataHoraEmp = dataHoraEmp;
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, copiaEBook,replicaServidor);
        emp.extenderEmprestimo();
        assertEquals(1,emp.getExtensaoEmprestimo());
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
