package com.epam.vitrenko.social.domain.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class UsernameAlreadyExistException extends RuntimeException {

    private static final String MESSAGE_FORMAT= "username %s already exists";

    @Getter
    private final String username;

    public UsernameAlreadyExistException(String username, Throwable cause) {
        super(String.format(MESSAGE_FORMAT, username), cause);
        this.username = username;
    }
}
