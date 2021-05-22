package Aplicacao;

public class EBook {
    private String ISBN;
    private String autor;
    private String titulo;
    private String editora;
    private String formato;
    private float fileSize;
    private String signature;

    public EBook(String ISBN, String autor, String titulo, String editora, String formato, float fileSize, String signature) {
        this.ISBN = ISBN;
        this.autor = autor;
        this.titulo = titulo;
        this.editora = editora;
        this.formato = formato;
        this.fileSize = fileSize;
        this.signature = signature;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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
        return fileSize;
    }

    public void setFileSize(float fileSize) {
        this.fileSize = fileSize;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
