import Aplicacao.CopiaEBook;
import Aplicacao.EBook;
import Aplicacao.Exceptions.InvalidCopiaEBookException;
import Aplicacao.Exceptions.InvalidEBookException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUnidade_CopiaEBook {

    private String ISBN = "akjshdahq123123";
    private String autor = "Stephen King";
    private String titulo = "The Shinning";
    private String editora = "Ray Lovejoy";
    private String formato = "pdf";
    private float fileSize = 150.f;
    private String signature = "Stephen king sig";
    private EBook eBook = new EBook(ISBN, autor, titulo, editora, formato, fileSize, signature);
    private int idCopia = 1;
    private CopiaEBook copiaEBook = null;

    public TestUnidade_CopiaEBook() throws InvalidEBookException {
    }


    @Test
    void createCopiaEBookNull() {
        assertNull(copiaEBook);
    }

    @Test
    void createCopiaEBookOK() throws InvalidCopiaEBookException {
        copiaEBook = new CopiaEBook(idCopia,eBook);
        assertNotNull(copiaEBook);
    }

    @Test
    void createCopiaEBookIdEquals0andNullEBook() {
        assertThrows(InvalidCopiaEBookException.class, () -> {
            copiaEBook = new CopiaEBook(0,null);
        });
    }
    @Test
    void createCopiaEBookIdEquals0andOKEBook() {
        assertThrows(InvalidCopiaEBookException.class, () -> {
            copiaEBook = new CopiaEBook(0,eBook);
        });
    }
    @Test
    void createCopiaEBookIdLess0andOKEBook() {
        assertThrows(InvalidCopiaEBookException.class, () -> {
            copiaEBook = new CopiaEBook(-1,eBook);
        });
    }
    @Test
    void createCopiaEBookIdLess0andWrongEBook() {
        assertThrows(InvalidCopiaEBookException.class, () -> {
            copiaEBook = new CopiaEBook(-1,null);
        });
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void AfterEach() {
    }

    @AfterAll
    static void AfterAll() {
    }
}
