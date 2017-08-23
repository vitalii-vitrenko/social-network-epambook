package com.epam.vitrenko.social.domain.service;

import com.epam.vitrenko.social.domain.dto.ProfileDto;
import com.epam.vitrenko.social.domain.entity.Profile;

public interface ProfileService {

    ProfileDto findByUsername(String username);

    void createProfile(ProfileDto profile);

    boolean usernameExists(String username);

}
