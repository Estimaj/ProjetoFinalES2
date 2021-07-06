package Aplicacao.Exceptions;

import java.io.Serializable;

public class InvalidEditoraException extends Exception implements Serializable {
    public InvalidEditoraException(String message) {
        super(message);
    }
}