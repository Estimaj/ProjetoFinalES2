import Aplicacao.*;
import Aplicacao.Exceptions.*;
import Visualizacao.Exceptions.FileExtensionException;
import Visualizacao.Exceptions.FileSizeException;
import Visualizacao.InterfaceComponenteWeb;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestComponenteWebAPIStub {

    private InterfaceComponenteWeb interfaceComponenteWeb = null;
    private EBook eBook = null;
    private CopiaEBook copiaEBook = null;
    private Utilizador user_ativo = new Utilizador(1,"Clark","Clark@exemplo.pt","Abc1abcABC","Krypton, Krypton","111-222-111","ativo");
    private Utilizador user_desativo = new Utilizador(2,"Phoebe","phoebe@exemplo.pt","Abc1abcABC","Londres, England","111-222-111","desativo");
    private ReplicaServidor replicaServidor_aveiro = new ReplicaServidor("Aveiro",copiaEBook);
    private Emprestimo emp = new Emprestimo(1,LocalDate.now(),LocalDate.now().plusMonths(1),user_desativo, copiaEBook, replicaServidor_aveiro,1);

    public TestComponenteWebAPIStub() throws InvalidUserException, InvalidReplicaException, EmprestimoException {
    }

    @Test
    void test_conexao_API_quando_formato_e_NULL() throws InvalidCopiaEBookException, InvalidEBookException {

        eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy",null,150.f,"Stephen king sig");
        copiaEBook = new CopiaEBook(1,eBook);

        Throwable exception = assertThrows(FileExtensionException.class, () -> {
            interfaceComponenteWeb.requestWebAPI(emp);
        });
        assertEquals(FileExtensionException.class, exception.getClass());

    }

    @Test
    void test_conexao_API_quando_formato_e_nao_e_pdf() throws InvalidCopiaEBookException, InvalidEBookException {

        eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","txt",150.f,"Stephen king sig");
        copiaEBook = new CopiaEBook(1,eBook);

        Throwable exception = assertThrows(FileExtensionException.class, () -> {
            interfaceComponenteWeb.requestWebAPI(emp);
        });
        assertEquals(FileExtensionException.class, exception.getClass());

    }

    @Test
    void getConectionAPISuccess() throws IOException, FileExtensionException, FileSizeException, InvalidCopiaEBookException, InvalidEBookException, InvalidVisualizacaoException {

        eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
        copiaEBook = new CopiaEBook(1,eBook);

        interfaceComponenteWeb.requestWebAPI(emp);
        assertEquals(200,interfaceComponenteWeb.getResponseCode());
        assertTrue(interfaceComponenteWeb.getContent().contains("pdf"));


        assertTrue(interfaceComponenteWeb.getRequestMethod().toLowerCase().contains("get"));
    }

    @Test
    void getConectionAPIFailure() throws IOException, FileExtensionException, FileSizeException, InvalidCopiaEBookException, InvalidEBookException, InvalidVisualizacaoException {

        eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
        copiaEBook = new CopiaEBook(1,eBook);

        interfaceComponenteWeb.requestWebAPI(emp);
        assertEquals(400,interfaceComponenteWeb.getResponseCode());
        assertTrue(interfaceComponenteWeb.getContent().contains("pdf"));


        assertTrue(interfaceComponenteWeb.getRequestMethod().toLowerCase().contains("get"));
    }

    @Test
    void test_conexao_API_File_Size_maior_do_que_permitido() throws InvalidCopiaEBookException, InvalidEBookException {

        eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
        copiaEBook = new CopiaEBook(1,eBook);

        Throwable exception = assertThrows(FileSizeException.class, () -> {
            interfaceComponenteWeb.requestWebAPI(emp);
        });
        assertEquals(FileSizeException.class, exception.getClass());

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
