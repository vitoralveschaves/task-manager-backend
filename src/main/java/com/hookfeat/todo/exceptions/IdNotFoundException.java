package com.hookfeat.todo.exceptions;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String message) {
        super(message);
    }

    public IdNotFoundException() {
        super("O identificador passado não corresponde a nenhum dos elementos");
    }
}
