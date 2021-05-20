import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUnidade_EBook {

    private EBook eBook = null;
    private String ISBN;
    private String autor;
    private String titulo;
    private String editora;
    private String formato;
    private Float fileSize = 0.f;
    private String signature;


    @Test
    void test_Criacao_EBook(){
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 150.0f;
        signature = "Stephen king sig";
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
    }

    @Test
    void test_Criacao_EBook_com_null_params(){
        eBook = new EBook(null,null,null,null,null,0.f,null);
        assertEquals(null,eBook.getISBN());
        assertEquals(null,eBook.getAutor());
        assertEquals(null,eBook.getTitulo());
        assertEquals(null,eBook.getEditora());
        assertEquals(null,eBook.getFormato());
        assertEquals(0.f,eBook.getFileSize());
        assertEquals(null,eBook.getSignature());
    }

    @Test
    void test_setISBN(){
        ISBN = "akjshdahq123123";
        autor = "Ross";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 150.0f;
        signature = "Stephen king";
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        assertEquals(ISBN,eBook.getISBN());
    }


    @Test
    void test_setautor(){
        ISBN = "akjshdahq123123";
        autor = "Ross";
        titulo = "The Haunting";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 150.0f;
        signature = "Stephen king";
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        assertEquals(autor,eBook.getAutor());
    }

    @Test
    void test_setTitulo(){
        ISBN = "akjshdahq123123";
        autor = "Rachel";
        titulo = "Little Woman";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 150.0f;
        signature = "dunno";
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        assertEquals(titulo,eBook.getTitulo());
    }

    @Test
    void test_setEditora(){
        ISBN = "akjshdahq123123";
        autor = "Ross";
        titulo = "The Night of the Living Dead";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 150.0f;
        signature = "Stephen king";
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        assertEquals(editora,eBook.getEditora());
    }

    @Test
    void test_setformato(){
        ISBN = "akjshdahq123123";
        autor = "Phoebe";
        titulo = "Justice League";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 150.0f;
        signature = "Stephen king";
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        assertEquals(formato,eBook.getFormato());
    }


    @Test
    void test_setFileSize(){
        ISBN = "akjshdahq123123";
        autor = "Capitao Americano";
        titulo = "Avengers";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 150.0f;
        signature = "Capitao Americano king";
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        assertEquals(fileSize,eBook.getFileSize());
    }

    @Test
    void test_setSignature(){
        ISBN = "akjshdahq123123";
        autor = "Elizabeth";
        titulo = "WandaVision";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 150.0f;
        signature = "Elizabeth King";
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        assertEquals(signature,eBook.getSignature());
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
