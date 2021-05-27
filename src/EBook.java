import Exceptions.InvalidEBookFormatException;
import Exceptions.InvalidEBookSignatureException;
import Exceptions.InvalidEBookSizeException;

public class EBook {
    private String ISBN;
    private String Autor;
    private String titulo;
    private String editora;
    private String formato;
    private Float FileSize;
    private String Signature;

    public EBook(String ISBN, String autor, String titulo, String editora, String formato, Float fileSize, String signature) throws InvalidEBookFormatException, InvalidEBookSizeException, InvalidEBookSignatureException {
        if(formato != "epub" && formato != "pdf"){
            throw new InvalidEBookFormatException("Formato incorreto.");
        }
        if(fileSize > 155.5f || fileSize < 0){
            throw new InvalidEBookSizeException("Ficheiro superior a 155.5 mb.");
        }
        if(signature == null){
            throw new InvalidEBookSignatureException("Ficheiro com signature incorreta.");
        }
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
