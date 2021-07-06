package Aplicacao;

import Aplicacao.Exceptions.InvalidEBookException;
import Aplicacao.Exceptions.InvalidEBookFormatException;
import Aplicacao.Exceptions.InvalidEBookSizeException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EBook {
    private int idEbook;
    private String ISBN;
    private String autor;
    private String titulo;
    private Editora editora;
    private String formato;
    private float fileSize;
    private String hash;
    private static final String NUMBER_VERIFICATION = ".*\\d.*";

    public EBook(int idEbook, String ISBN, String autor, String titulo, Editora editora, String formato, float fileSize, String hash) throws InvalidEBookException, InvalidEBookFormatException, InvalidEBookSizeException {

        if (idEbook <= 0 || idEbook >= 30001)
            throw new InvalidEBookException("EBook invalido Id");

        if (autor == null || autor.matches(NUMBER_VERIFICATION) || autor.equals(""))
            throw new InvalidEBookException("Invalid EBook Exception");

        if (editora == null)
            throw new InvalidEBookException("Editora Null");

        if (hash == null || hash.equals(""))
            throw new InvalidEBookException("Ficheiro com signature incorreta.");

        if(formato != "epub" && formato != "pdf"){
            throw new InvalidEBookFormatException("Formato incorreto.");
        }
        if(fileSize < 0 || fileSize > 155.5f ){
            throw new InvalidEBookSizeException("Ficheiro superior a 155.5 mb.");
        }

        this.idEbook = idEbook;
        this.ISBN = ISBN;
        this.autor = autor;
        this.titulo = titulo;
        this.editora = editora;
        this.formato = formato;
        this.fileSize = fileSize;
        try {
            this.hash = getHash(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public int getIdEbook() {
        return idEbook;
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

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
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

    public String getHash() {
        return hash;
    }

    public void setSignature(String signature) {
        this.hash = signature;
    }

    private String getHash(String str) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }
}
