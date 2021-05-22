import Aplicacao.CopiaEBook;
import Aplicacao.EBook;
import Visualizacao.Exceptions.FileExtensionException;
import Visualizacao.Exceptions.FileSizeException;
import Visualizacao.InterfaceComponenteWeb;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestComponenteWebAPIStub {

    private InterfaceComponenteWeb interfaceComponenteWeb = null;
    private EBook eBook = null;
    private CopiaEBook copiaEBook = null;

    @Test
    void test_conexao_API_quando_formato_e_NULL() {

        eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy",null,150.f,"Stephen king sig");
        copiaEBook = new CopiaEBook(1,eBook);

        Throwable exception = assertThrows(FileExtensionException.class, () -> {
            interfaceComponenteWeb.requestWebAPI(copiaEBook);
        });
        assertEquals(FileExtensionException.class, exception.getClass());

    }

    @Test
    void test_conexao_API_quando_formato_e_nao_e_pdf() {

        eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","txt",150.f,"Stephen king sig");
        copiaEBook = new CopiaEBook(1,eBook);

        Throwable exception = assertThrows(FileExtensionException.class, () -> {
            interfaceComponenteWeb.requestWebAPI(copiaEBook);
        });
        assertEquals(FileExtensionException.class, exception.getClass());

    }

    @Test
    void test_conexao_API_Sucesso() throws IOException, FileExtensionException, FileSizeException {

        eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
        copiaEBook = new CopiaEBook(1,eBook);

        interfaceComponenteWeb.requestWebAPI(copiaEBook);
        assertEquals(200,interfaceComponenteWeb.getResponseCode());
        assertTrue(interfaceComponenteWeb.getContent().contains("pdf"));


        assertTrue(interfaceComponenteWeb.getRequestMethod().toLowerCase().contains("get"));
    }

    @Test
    void test_conexao_API_Failure() throws IOException, FileExtensionException, FileSizeException {

        eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
        copiaEBook = new CopiaEBook(1,eBook);

        interfaceComponenteWeb.requestWebAPI(copiaEBook);
        assertEquals(400,interfaceComponenteWeb.getResponseCode());
        assertTrue(interfaceComponenteWeb.getContent().contains("pdf"));


        assertTrue(interfaceComponenteWeb.getRequestMethod().toLowerCase().contains("get"));
    }

    @Test
    void test_conexao_API_File_Size_maior_do_que_permitido() {

        eBook = new EBook("akjshdahq123123","Stephen King","The Shinning","Ray Lovejoy","pdf",150.f,"Stephen king sig");
        copiaEBook = new CopiaEBook(1,eBook);

        Throwable exception = assertThrows(FileSizeException.class, () -> {
            interfaceComponenteWeb.requestWebAPI(copiaEBook);
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
