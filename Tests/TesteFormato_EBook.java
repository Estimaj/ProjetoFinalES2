import Exceptions.InvalidEBookFormatException;
import Exceptions.InvalidEBookSignatureException;
import Exceptions.InvalidEBookSizeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TesteFormato_EBook {

    private EBook eBook = null;
    private String ISBN;
    private String autor;
    private String titulo;
    private String editora;
    private String formato;
    private Float fileSize = 0.f;
    private String signature;

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
        signature = "Stephen king sig";
        assertThrows(InvalidEBookFormatException.class, () -> {
            EBook copiaEBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
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
        signature = "Stephen king sig";
        assertThrows(InvalidEBookFormatException.class, () -> {
            EBook copiaEBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        });
    }

    /**
     *  Formato correto com o formato pdf
     *
     *  return InvalidEBookFormatException
     */
    @Test
    void test_formato_correto_Pdf_EBook() throws InvalidEBookFormatException, InvalidEBookSizeException, InvalidEBookSignatureException {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 150.0f;
        signature = "Stephen king sig";
        EBook copiaEBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        assertEquals("pdf", copiaEBook.getFormato());
    }

    /**
     *  Formato correto com o formato epub
     *
     *  return InvalidEBookFormatException
     */
    @Test
    void test_formato_correto_EPub_EBook() throws InvalidEBookFormatException, InvalidEBookSizeException, InvalidEBookSignatureException {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "epub";
        fileSize = 150.0f;
        signature = "Stephen king sig";
        EBook copiaEBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        assertEquals(formato, copiaEBook.getFormato());
    }
}
