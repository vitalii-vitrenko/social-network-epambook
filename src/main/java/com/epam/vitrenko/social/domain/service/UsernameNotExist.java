package com.epam.vitrenko.social.domain.service;

import lombok.Getter;

public class UsernameNotExist extends RuntimeException {

    private static final String MESSAGE_FORMAT = "username %s does not exists";

    @Getter
    private final String username;

    public UsernameNotExist(String username) {
        super(String.format(MESSAGE_FORMAT, username));
        this.username = username;
    }
}
