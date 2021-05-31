package Aplicacao.Exceptions;

import java.io.Serializable;

public class InvalidEBookSizeException extends Exception implements Serializable {
    public InvalidEBookSizeException(String message) {
        super(message);
    }
}