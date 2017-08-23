package com.epam.vitrenko.social.domain.service;

import com.epam.vitrenko.social.domain.dto.ProfileDto;
import com.epam.vitrenko.social.domain.dto.ProfileMapper;
import com.epam.vitrenko.social.domain.entity.Profile;
import com.epam.vitrenko.social.domain.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    private ProfileMapper profileMapper;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    public ProfileDto findByUsername(String username) {
        Objects.requireNonNull(username, "username must not be null");
        Profile profile = profileRepository.findByUsername(username);
        return profileMapper.mapToDto(profile);
    }

    @Override
    public void createProfile(ProfileDto profileDto) {
        Objects.requireNonNull(profileDto, "profile must not be null");
        try {
            profileRepository.save(profileMapper.mapFromDto(profileDto));
        } catch (DataIntegrityViolationException ex) {
            throw new UsernameAlreadyExistException(profileDto.getUsername(), ex);
        }
    }

    @Override
    public boolean usernameExists(String username) {
        return findByUsername(username) != null;
    }

}
