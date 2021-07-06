import Aplicacao.Editora;
import Aplicacao.Exceptions.InvalidEditoraException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUnidade_Editora {

    private int idEditora = 1;
    private String nomeEditora = "LEYA";
    private Editora editora;

    @Test
    void createEditoraOK() throws InvalidEditoraException {
        editora = new Editora(idEditora,nomeEditora);
        assertNotNull(editora);
    }

    @Test
    void CreateEditoraIdEBookLessThen0() {
        assertThrows(InvalidEditoraException.class, () -> {
            editora = new Editora(-1,nomeEditora);
        });
    }

    @Test
    void CreateEditoraIdEBookEquals0() {
        assertThrows(InvalidEditoraException.class, () -> {
            editora = new Editora(0,nomeEditora);
        });
    }

    @Test
    void CreateEditoraIdEBookGreat0() throws InvalidEditoraException {
        editora = new Editora(1,nomeEditora);
        assertEquals(1,editora.getId_editora());
    }

    @Test
    void CreateEditoraIdEBookGreatMax() {
        assertThrows(InvalidEditoraException.class, () -> {
            editora = new Editora(30002,nomeEditora);
        });
    }

    @Test
    void CreateEditoraIdEBookEqualsMax() {
        assertThrows(InvalidEditoraException.class, () -> {
            editora = new Editora(30001,nomeEditora);
        });
    }

    @Test
    void CreateEditoraIdEBookLessMax() throws InvalidEditoraException {
        editora = new Editora(30000,nomeEditora);
        assertEquals(30000,editora.getId_editora());
    }

    @Test
    void CreateEditoraName_Empty() {
        assertThrows(InvalidEditoraException.class, () -> {
            editora = new Editora(1,"");
        });
    }

    @Test
    void CreateEditoraName_Null() {
        assertThrows(InvalidEditoraException.class, () -> {
            editora = new Editora(1,null);
        });
    }

    @Test
    void CreateEditoraName_Exceeds_Max_Caracteres() {
        assertThrows(InvalidEditoraException.class, () -> {
            editora = new Editora(1,"LEYALEYALEYALEYALEYALEYALEYA");
        });
    }
}
