package com.epam.vitrenko.social.domain.service;

import lombok.Getter;

@Getter
public class NotFriendsException extends FriendsException {

    private static final String MESSAGE_FORMAT = "%s and %s are not friends";

    public NotFriendsException(String username, String friendsUsername) {
        super(MESSAGE_FORMAT, username, friendsUsername);
    }
}
