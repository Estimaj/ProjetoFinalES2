import Aplicacao.EBook;
import Aplicacao.Exceptions.InvalidEBookException;
import Aplicacao.Exceptions.InvalidEBookFormatException;
import Aplicacao.Exceptions.InvalidEBookSizeException;
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

    private int idEbook = 1;
    private EBook eBook = null;
    private String ISBN = "akjshdahq123123";
    private String autor = "Stephen King";
    private String titulo = "The Shinning";
    private String editora = "Ray Lovejoy";
    private String formato = "pdf";
    private Float fileSize = 0.f;
    private String hash = "Stephen king sig";

    @Test
    void createEBookOK() throws InvalidEBookException, InvalidEBookSizeException, InvalidEBookFormatException {
        eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertNotNull(eBook);
    }

    @Test
    void createEBookNull() {
        assertNull(eBook);
    }

    @Test
    void CreateEBookIdEBookOK() throws InvalidEBookFormatException, InvalidEBookSizeException, InvalidEBookException {
        eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(1,eBook.getIdEbook());
    }
    @Test
    void CreateEBookIdEBookLessThen0() {
        assertThrows(InvalidEBookException.class, () -> {
            eBook = new EBook(-1,ISBN,autor,titulo,editora,formato,fileSize,hash);
        });
    }

    @Test
    void CreateEBookIdEBookEquals0() {
        assertThrows(InvalidEBookException.class, () -> {
            eBook = new EBook(0,ISBN,autor,titulo,editora,formato,fileSize,hash);
        });
    }

    @Test
    void CreateEBookIdEBookGreatMax() {
        assertThrows(InvalidEBookException.class, () -> {
            eBook = new EBook(40000,ISBN,autor,titulo,editora,formato,fileSize,hash);
        });
    }

    @Test
    void CreateEBookWithNullParams() {
        assertThrows(InvalidEBookException.class, () -> {
            eBook = new EBook(idEbook,null,null,null,null,null,0.f,null);
        });
    }

    @Test
    void test_setISBN() throws InvalidEBookException, InvalidEBookSizeException, InvalidEBookFormatException {
        eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(ISBN,eBook.getISBN());
    }


    @Test
    void createEBookAutorOK() throws InvalidEBookException, InvalidEBookSizeException, InvalidEBookFormatException {
        eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(autor,eBook.getAutor());
    }

    @Test
    void createEBookAutorNull() {
        assertThrows(InvalidEBookException.class, () -> {
            eBook = new EBook(idEbook,ISBN,null,titulo,editora,formato,fileSize,hash);
        });
    }

    @Test
    void createEBookWrongAutorNumber() {
        assertThrows(InvalidEBookException.class, () -> {
            eBook = new EBook(idEbook,ISBN,"1",titulo,editora,formato,fileSize,hash);
        });
    }

    @Test
    void createEBookWrongAutorEmpty() {
        assertThrows(InvalidEBookException.class, () -> {
            eBook = new EBook(idEbook,ISBN,"",titulo,editora,formato,fileSize,hash);
        });
    }

    @Test
    void test_setTitulo() throws InvalidEBookException, InvalidEBookSizeException, InvalidEBookFormatException {
        eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(titulo,eBook.getTitulo());
    }

    @Test
    void test_setEditora() throws InvalidEBookException, InvalidEBookSizeException, InvalidEBookFormatException {
        eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(editora,eBook.getEditora());
    }

    @Test
    void test_setformato() throws InvalidEBookException, InvalidEBookSizeException, InvalidEBookFormatException {
        eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(formato,eBook.getFormato());
    }


    @Test
    void test_setFileSize() throws InvalidEBookException, InvalidEBookSizeException, InvalidEBookFormatException {
        eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(fileSize,eBook.getFileSize());
    }


    @Test
    void createEBookHashOK() throws InvalidEBookException, NoSuchAlgorithmException, InvalidEBookSizeException, InvalidEBookFormatException {
        hash = check_hash(this.hash);
        eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(check_hash(this.hash),eBook.getHash());
    }

    @Test
    void createEBookHashNull() {
        hash = null;
        assertThrows(InvalidEBookException.class, () -> {
            eBook = new EBook(idEbook,ISBN,"",titulo,editora,formato,fileSize,hash);
        });
    }

    @Test
    void createEBookHashEmpty() {
        hash = "";
        assertThrows(InvalidEBookException.class, () -> {
            eBook = new EBook(idEbook,ISBN,"",titulo,editora,formato,fileSize,hash);
        });
    }

    /**
     *  Formato não permitido
     *
     *  return InvalidEBookFormatException
     */
    @Test
    void test_formato_EBook() {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "html";
        fileSize = 150.0f;
        hash = "Stephen king sig";
        assertThrows(InvalidEBookFormatException.class, () -> {
            EBook eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        });
    }

    /**
     *  Formato null
     *
     *  return InvalidEBookFormatException
     */
    @Test
    void test_formato_null_EBook() {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= null;
        fileSize = 150.0f;
        hash = "Stephen king sig";
        assertThrows(InvalidEBookFormatException.class, () -> {
            EBook eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        });
    }

    /**
     *  Formato correto com o formato pdf
     *
     *  return InvalidEBookFormatException
     */
    @Test
    void test_formato_correto_Pdf_EBook() throws InvalidEBookException, InvalidEBookSizeException, InvalidEBookFormatException {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 150.0f;
        hash = "Stephen king sig";
        EBook eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals("pdf", eBook.getFormato());
    }

    /**
     *  Formato correto com o formato epub
     *
     *  return InvalidEBookFormatException
     */
    @Test
    void test_formato_correto_EPub_EBook() throws InvalidEBookFormatException, InvalidEBookSizeException, InvalidEBookException {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "epub";
        fileSize = 150.0f;
        hash = "Stephen king sig";
        EBook eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(formato, eBook.getFormato());
    }

    /**
     *  SizeFile Superior a 5.5, não permitido
     *
     *  return InvalidEBookSizeException
     */
    @Test
    void test_SizeFile_Superior_EBook() {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 155.6f;
        hash = "Stephen king sig";
        assertThrows(InvalidEBookSizeException.class, () -> {
            EBook eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        });
    }

    /**
     *  SizeFile Inferior a 0, não permitido
     *
     *  return InvalidEBookSizeException
     */
    @Test
    void test_SizeFile_Inferior_EBook() {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = -155.5f;
        hash = "Stephen king sig";
        assertThrows(InvalidEBookSizeException.class, () -> {
            EBook eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        });
    }

    /**
     *  SizeFile in range [0, 155.5], permitido
     *
     *  return true
     */
    @Test
    void test_SizeFile_Correto_EBook() throws InvalidEBookFormatException, InvalidEBookSizeException, InvalidEBookException {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 120f;
        hash = "Stephen king sig";
        EBook eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(fileSize, eBook.getFileSize());
    }

    /**
     *  SizeFile is 0, permitido
     *
     *  return true
     */
    @Test
    void test_SizeFile_Correto_0_EBook() throws InvalidEBookFormatException, InvalidEBookSizeException, InvalidEBookException {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 0f;
        hash = "Stephen king sig";
        EBook eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(fileSize, eBook.getFileSize());
    }

    /**
     *  SizeFile is 155.5, permitido
     *
     *  return true
     */
    @Test
    void test_SizeFile_Correto_155_5_EBook() throws InvalidEBookFormatException, InvalidEBookSizeException, InvalidEBookException {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 155.5f;
        hash = "Stephen king sig";
        EBook eBook = new EBook(idEbook,ISBN,autor,titulo,editora,formato,fileSize,hash);
        assertEquals(fileSize, eBook.getFileSize());
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
