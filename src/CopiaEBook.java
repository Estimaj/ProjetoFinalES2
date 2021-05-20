public class CopiaEBook {

    private int id;
    private EBook eBook;

    public CopiaEBook(int id, EBook eBook) {
        this.id = id;
        this.eBook = eBook;
    }

    public int getId() {
        return id;
    }

    public EBook getLivro() {
        return eBook;
    }
}
