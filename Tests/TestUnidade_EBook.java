import Aplicacao.EBook;
import Aplicacao.Exceptions.InvalidEBookException;
import Aplicacao.Utilizador;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

public class TestUnidade_EBook {

    private EBook eBook = null;
    private String ISBN = "akjshdahq123123";
    private String autor = "Stephen King";
    private String titulo = "The Shinning";
    private String editora = "Ray Lovejoy";
    private String formato = "pdf";
    private Float fileSize = 0.f;
    private String hash = "Stephen king sig";


    @Test
    void createEBookAutor() {
        assertNotNull(eBook);
    }

    @Test
    void test_Criacao_EBook_com_null_params() throws InvalidEBookException {
        eBook = new EBook(null,null,null,null,null,0.f,null);
        assertNull(eBook.getISBN());
        assertNull(eBook.getAutor());
        assertNull(eBook.getTitulo());
        assertNull(eBook.getEditora());
        assertNull(eBook.getFormato());
        assertEquals(0.f,eBook.getFileSize());
        assertNull(eBook.getHash());
    }

    @Test
    void test_setISBN() throws InvalidEBookException {
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(ISBN,eBook.getISBN());
    }


    @Test
    void createEBookAutorOK() throws InvalidEBookException {
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(autor,eBook.getAutor());
    }

    @Test
    void createEBookAutorNull() {
        assertThrows(InvalidEBookException.class, () -> {
            eBook = new EBook(ISBN,null,titulo,editora,formato,fileSize,hash);
        });
    }

    @Test
    void createEBookWrongAutorNumber() {
        assertThrows(InvalidEBookException.class, () -> {
            eBook = new EBook(ISBN,"1",titulo,editora,formato,fileSize,hash);
        });
    }

    @Test
    void createEBookWrongAutorEmpty() {
        assertThrows(InvalidEBookException.class, () -> {
            eBook = new EBook(ISBN,"",titulo,editora,formato,fileSize,hash);
        });
    }

    @Test
    void test_setTitulo() throws InvalidEBookException {
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(titulo,eBook.getTitulo());
    }

    @Test
    void test_setEditora() throws InvalidEBookException {
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(editora,eBook.getEditora());
    }

    @Test
    void test_setformato() throws InvalidEBookException {
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(formato,eBook.getFormato());
    }


    @Test
    void test_setFileSize() throws InvalidEBookException {
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(fileSize,eBook.getFileSize());
    }

    @Test
    void test_sethash() throws InvalidEBookException {
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(hash,eBook.getHash());
    }

    @Test
    void createEBookHashOK() throws InvalidEBookException, NoSuchAlgorithmException {
        hash = check_hash(hash);
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(hash,eBook.getHash());
    }

    @Test
    void createEBookHashNull() {
        hash = null;
        assertThrows(InvalidEBookException.class, () -> {
            eBook = new EBook(ISBN,"",titulo,editora,formato,fileSize,hash);
        });
    }

    @Test
    void createEBookHashEmpty() {
        hash = "";
        assertThrows(InvalidEBookException.class, () -> {
            eBook = new EBook(ISBN,"",titulo,editora,formato,fileSize,hash);
        });
    }

    private String check_hash(String str) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
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
