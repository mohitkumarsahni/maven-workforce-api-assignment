package org.mavenworkforce.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("User with ID "+id+" does not exist in system.");
    }
}
