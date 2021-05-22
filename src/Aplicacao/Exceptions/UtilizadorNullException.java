package Aplicacao.Exceptions;

import java.io.Serializable;

public class UtilizadorNullException extends Exception implements Serializable {
    public UtilizadorNullException(String message) {
        super(message);
    }
}

