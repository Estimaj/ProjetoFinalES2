package Exceptions;

import java.io.Serializable;

public class AssinaturaEmprestimoException extends Exception implements Serializable {
    public AssinaturaEmprestimoException(String message) {
        super(message);
    }
}

