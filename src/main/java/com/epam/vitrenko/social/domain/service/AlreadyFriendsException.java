package com.epam.vitrenko.social.domain.service;

import lombok.Getter;

@Getter
public class AlreadyFriendsException extends FriendsException {

    private static final String MESSAGE_FORMAT = "%s and %s are already friends";

    public AlreadyFriendsException(String username, String friendUsername) {
        super(MESSAGE_FORMAT, username, friendUsername);
    }
}
