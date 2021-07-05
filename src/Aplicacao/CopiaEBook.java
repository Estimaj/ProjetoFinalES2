package Aplicacao;

import Aplicacao.Exceptions.InvalidCopiaEBookException;

public class CopiaEBook {

    private int id;
    private EBook eBook;

    public CopiaEBook(int id, EBook eBook) throws InvalidCopiaEBookException {
        if (id <= 0 || id >= 30001)
            throw new InvalidCopiaEBookException("Invalid Copia ID EBook Exception");
        if (eBook == null)
            throw new InvalidCopiaEBookException("Invalid Copia EBook Exception");

        this.id = id;
        this.eBook = eBook;
    }

    public int getId() {
        return id;
    }

    public EBook getEBook() {
        return eBook;
    }

    public void setId(int id) throws InvalidCopiaEBookException {
        if (id <= 0)
            throw new InvalidCopiaEBookException("Invalid Copia EBook Exception id");
        this.id = id;
    }

    public void seteBook(EBook eBook) throws InvalidCopiaEBookException {
        if (eBook == null)
            throw new InvalidCopiaEBookException("Invalid Copia EBook Exception id");
        this.eBook = eBook;
    }
}
