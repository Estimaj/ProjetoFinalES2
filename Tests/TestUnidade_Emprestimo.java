import Exceptions.ExtensaoEmprestimoException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUnidade_Emprestimo {

    private Emprestimo emp = null;
    private Integer id_emp = null;
    private LocalDate dataHoraEmp = null;
    private LocalDate FimdataHoraEmp = null;
    private Integer extensaoEmprestimo = null;
    private Utilizador user = new Utilizador(1,"Clark","Clark@exemplo.pt","Krypton","111","ativo");
    private EBook eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
    private CopiaEBook copiaEBook = new CopiaEBook(1,eBook);

    @Test
    void test_Criacao_Emprestimo(){
        id_emp = 1;
        dataHoraEmp = LocalDate.now();
        FimdataHoraEmp = dataHoraEmp;
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, copiaEBook);
    }

    @Test
    void test_Criacao_Emprestimo_com_null_params(){
        emp = new Emprestimo(0,null,null,null,null);
        assertEquals(0,emp.getId_emp());
        assertEquals(null,emp.getDataHoraEmp());
        assertEquals(null,emp.getFimdataHoraEmp());
    }

    @Test
    void test_setIdEmprestimo(){
        id_emp = 2;
        dataHoraEmp = LocalDate.now();
        FimdataHoraEmp = dataHoraEmp;
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, copiaEBook);
        assertEquals(id_emp,emp.getId_emp());
    }

    @Test
    void test_setDataHoraEmp(){
        id_emp = 2;
        dataHoraEmp = LocalDate.now();
        FimdataHoraEmp = dataHoraEmp;
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, copiaEBook);
        assertEquals(dataHoraEmp,emp.getDataHoraEmp());
    }

    @Test
    void test_setFimdataHoraEmp(){
        id_emp = 2;
        dataHoraEmp = LocalDate.now();
        FimdataHoraEmp = dataHoraEmp;
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, copiaEBook);
        assertEquals(FimdataHoraEmp,emp.getFimdataHoraEmp());
    }

    @Test
    void test_setextensaoEmprestimo() throws ExtensaoEmprestimoException {
        id_emp = 2;
        dataHoraEmp = LocalDate.now();
        FimdataHoraEmp = dataHoraEmp;
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, copiaEBook);
        emp.extenderEmprestimo();
        assertEquals(1,emp.getExtensaoEmprestimo());
    }
    @Test
    void test_setextensaoEmprestimo_2_vezes() throws ExtensaoEmprestimoException {
        id_emp = 2;
        dataHoraEmp = LocalDate.now();
        FimdataHoraEmp = dataHoraEmp;
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, copiaEBook);
        emp.extenderEmprestimo();
        emp.extenderEmprestimo();
        assertEquals(2,emp.getExtensaoEmprestimo());
    }
    @Test
    void test_setextensaoEmprestimo_Exception() throws ExtensaoEmprestimoException {
        id_emp = 2;
        dataHoraEmp = LocalDate.now();
        FimdataHoraEmp = dataHoraEmp;
        emp = new Emprestimo(id_emp,dataHoraEmp,FimdataHoraEmp,user, copiaEBook);
        emp.extenderEmprestimo();
        assertEquals(1,emp.getExtensaoEmprestimo());
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
