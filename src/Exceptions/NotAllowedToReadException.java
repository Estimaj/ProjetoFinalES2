package Exceptions;

import java.io.Serializable;

public class NotAllowedToReadException extends Exception implements Serializable {
    public NotAllowedToReadException(String message) {
        super(message);
    }
}