package Exceptions;

import java.io.Serializable;

public class ExtensaoEmprestimoException extends Exception implements Serializable {
    public ExtensaoEmprestimoException(String message) {
        super(message);
    }
}
