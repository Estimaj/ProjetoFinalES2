public class EBook {
    private String ISBN;
    private String Autor;
    private String titulo;
    private String editora;
    private String formato;
    private Float FileSize;
    private String Signature;

    public EBook(String ISBN, String autor, String titulo, String editora, String formato, Float fileSize, String signature) {
        this.ISBN = ISBN;
        this.Autor = autor;
        this.titulo = titulo;
        this.editora = editora;
        this.formato = formato;
        this.FileSize = fileSize;
        this.Signature = signature;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public float getFileSize() {
        return FileSize;
    }

    public void setFileSize(float fileSize) {
        FileSize = fileSize;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }
}
