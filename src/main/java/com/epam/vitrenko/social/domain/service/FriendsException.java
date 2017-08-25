package com.epam.vitrenko.social.domain.service;

import lombok.Getter;

@Getter
public abstract class FriendsException extends RuntimeException {

    private final String username;

    private final String friendsUsername;

    public FriendsException(String username, String friendUsername, String messageFormat) {
        super(String.format(messageFormat, username, friendUsername));
        this.username = username;
        this.friendsUsername = username;
    }
}
