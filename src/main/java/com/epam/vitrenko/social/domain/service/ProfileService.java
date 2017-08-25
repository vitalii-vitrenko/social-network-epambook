package com.epam.vitrenko.social.domain.service;

import com.epam.vitrenko.social.domain.dto.ProfileDto;

import java.util.Collection;
import java.util.Optional;

public interface ProfileService {

    Optional<ProfileDto> findByUsername(String username);

    void createProfile(ProfileDto profile);

    void makeFriends(String username, String friendUsername);

    Collection<ProfileDto> findFriends(String username);

    boolean isFriends(String username, String friendUsername);
}
