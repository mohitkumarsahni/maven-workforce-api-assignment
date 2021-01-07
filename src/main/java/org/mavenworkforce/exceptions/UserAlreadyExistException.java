package org.mavenworkforce.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String id) {
        super("User with ID "+id+" already exist in system.");
    }
}
