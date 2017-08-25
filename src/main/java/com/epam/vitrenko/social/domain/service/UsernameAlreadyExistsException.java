package com.epam.vitrenko.social.domain.service;

import lombok.Getter;

public class UsernameAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "username %s already exists";

    @Getter
    private final String username;

    public UsernameAlreadyExistsException(String username) {
        super(String.format(MESSAGE_FORMAT, username));
        this.username = username;
    }
}
