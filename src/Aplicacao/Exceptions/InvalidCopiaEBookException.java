package Aplicacao.Exceptions;

import java.io.Serializable;

public class InvalidCopiaEBookException extends Exception implements Serializable {
    public InvalidCopiaEBookException(String message) {
        super(message);
    }
}