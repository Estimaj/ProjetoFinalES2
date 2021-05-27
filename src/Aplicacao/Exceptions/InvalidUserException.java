package Aplicacao.Exceptions;

import java.io.Serializable;

public class InvalidUserException extends Exception implements Serializable {
    public InvalidUserException(String message) {
        super(message);
    }
}
