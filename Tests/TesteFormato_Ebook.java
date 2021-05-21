import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TesteFormato_Ebook {

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
        formato= "html";
        fileSize = 150.0f;
        signature = "Stephen king sig";
        eBook = new EBook(ISBN,autor,titulo,editora,formato,fileSize,signature);
        assertNull(eBook); // Comparar com string na consola "Code 400"
    }
}
