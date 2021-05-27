import Exceptions.InvalidEBookSignatureException;
import Exceptions.InvalidEBookSizeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TesteSignature_EBook {
    private EBook eBook = null;
    private String ISBN;
    private String autor;
    private String titulo;
    private String editora;
    private String formato;
    private Float fileSize = 0.f;
    private String signature;

    /**
     *  SizeFile Signature Null, não permitido
     *
     *  return InvalidEBookSignatureException
     */
    @Test
    void test_Signature_Null_EBook() {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 155.5f;
        signature = null;
        assertThrows(InvalidEBookSignatureException.class, () -> {
            EBook copiaEBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        });
    }

    /**
     *  SizeFile Superior a 5.5, não permitido
     *
     *  return true
     */
    @Test
    void test_Signature_EBook() {
        ISBN = "akjshdahq123123";
        autor = "Stephen King";
        titulo = "The Shinning";
        editora = "Ray Lovejoy";
        formato= "pdf";
        fileSize = 155.5f;
        signature = "Stephen king sig";
        assertThrows(InvalidEBookSignatureException.class, () -> {
            EBook copiaEBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        });
    }
}
