package Aplicacao;

import Aplicacao.Exceptions.InvalidEBookException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EBook {
    private String ISBN;
    private String autor;
    private String titulo;
    private String editora;
    private String formato;
    private float fileSize;
    private String hash;
    private static final String NUMBER_VERIFICATION = ".*\\d.*";

    public EBook(String ISBN, String autor, String titulo, String editora, String formato, float fileSize, String hash) throws InvalidEBookException {

        if (autor == null || autor.matches(NUMBER_VERIFICATION) || autor.equals(""))
            throw new InvalidEBookException("Invalid EBook Exception");

        if (hash == null || hash.equals(""))
            throw new InvalidEBookException("Invalid EBook Exception");


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

    private String getHash(String str) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
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

    public String getHash() {
        return hash;
    }

    public void setSignature(String signature) {
        this.hash = signature;
    }
}
