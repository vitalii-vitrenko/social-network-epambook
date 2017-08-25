package com.epam.vitrenko.social.domain.service;

import com.epam.vitrenko.social.domain.entity.Profile;
import com.epam.vitrenko.social.domain.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class ProfileUtils {

    private final ProfileRepository profileRepository;

    Profile findOrThrow(String username) {
        return profileRepository.findByUsername(username);
    }

    boolean isFriendsWith(String username, String friendUsername) {
        return profileRepository.isFriends(username, friendUsername);
    }
}
