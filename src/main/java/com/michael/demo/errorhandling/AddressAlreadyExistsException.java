package com.michael.demo.errorhandling;

public class AddressAlreadyExistsException extends RuntimeException {
    public AddressAlreadyExistsException(String message) {
        super(message);
    }
}
