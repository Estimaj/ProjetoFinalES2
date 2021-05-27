package Aplicacao.Exceptions;

import java.io.Serializable;

public class InvalidVisualizacaoException extends Exception implements Serializable {
    public InvalidVisualizacaoException(String message) {
        super(message);
    }
}