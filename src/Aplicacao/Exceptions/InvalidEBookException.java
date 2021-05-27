package Aplicacao.Exceptions;

import java.io.Serializable;

public class InvalidEBookException extends Exception implements Serializable {
    public InvalidEBookException(String message) {
        super(message);
    }
}