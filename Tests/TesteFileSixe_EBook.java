import Exceptions.InvalidEBookFormatException;
import Exceptions.InvalidEBookSignatureException;
import Exceptions.InvalidEBookSizeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TesteFileSixe_EBook {
    private EBook eBook = null;
    private String ISBN;
    private String autor;
    private String titulo;
    private String editora;
    private String formato;
    private Float fileSize = 0.f;
    private String signature;

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
        signature = "Stephen king sig";
        assertThrows(InvalidEBookSizeException.class, () -> {
            EBook copiaEBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        });
    }

    /**
     *  SizeFile Inferior a 0, não permitido
     *
     *  return InvalidEBookSizeException
     */
    @Test
    void test_SizeFile_Inferior_EBook() throws InvalidEBookSizeException, InvalidEBookFormatException, InvalidEBookSignatureException {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = -155.5f;
        signature = "Stephen king sig";
        assertThrows(InvalidEBookSizeException.class, () -> {
            EBook copiaEBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        });
    }

    /**
     *  SizeFile in range [0, 155.5], permitido
     *
     *  return true
     */
    @Test
    void test_SizeFile_Correto_EBook() throws InvalidEBookSizeException, InvalidEBookFormatException, InvalidEBookSignatureException {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 120f;
        signature = "Stephen king sig";
        EBook copiaEBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        assertEquals(fileSize, copiaEBook.getFileSize());
    }

    /**
     *  SizeFile is 0, permitido
     *
     *  return true
     */
    @Test
    void test_SizeFile_Correto_0_EBook() throws InvalidEBookSizeException, InvalidEBookFormatException, InvalidEBookSignatureException {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 0f;
        signature = "Stephen king sig";
        EBook copiaEBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        assertEquals(fileSize, copiaEBook.getFileSize());
    }

    /**
     *  SizeFile is 155.5, permitido
     *
     *  return true
     */
    @Test
    void test_SizeFile_Correto_155_5_EBook() throws InvalidEBookSizeException, InvalidEBookFormatException, InvalidEBookSignatureException {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 155.5f;
        signature = "Stephen king sig";
        EBook copiaEBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        assertEquals(fileSize, copiaEBook.getFileSize());
    }
}
