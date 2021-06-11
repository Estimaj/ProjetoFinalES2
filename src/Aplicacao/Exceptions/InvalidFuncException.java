package Aplicacao.Exceptions;

import java.io.Serializable;

public class InvalidFuncException extends Exception implements Serializable {
    public InvalidFuncException(String message) {
        super(message);
    }
}