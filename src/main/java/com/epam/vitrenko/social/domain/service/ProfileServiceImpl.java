package com.epam.vitrenko.social.domain.service;

import com.epam.vitrenko.social.domain.dto.ProfileDto;
import com.epam.vitrenko.social.domain.dto.ProfileMapper;
import com.epam.vitrenko.social.domain.entity.Profile;
import com.epam.vitrenko.social.domain.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
@Transactional
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    private final ProfileMapper profileMapper;

    private final ProfileUtils profileUtils;

    @Override
    public Optional<ProfileDto> findByUsername(String username) {
        Objects.requireNonNull(username, "username must not be null");

        Profile profile = profileRepository.findByUsername(username);
        return Optional.ofNullable(profileMapper.mapToDto(profile));
    }

    @Override
    public void createProfile(ProfileDto profileDto) {
        Objects.requireNonNull(profileDto, "profile must not be null");
        throwIfExists(profileDto.getUsername());

        try {
            profileRepository.save(profileMapper.mapFromDto(profileDto));
        } catch (DataIntegrityViolationException ex) {
            throw new UsernameAlreadyExistsException(profileDto.getUsername());
        }
    }

    public void makeFriends(String username, String friendUsername) {
        Profile profile = profileUtils.findOrThrow(Objects.requireNonNull(username));
        Profile friendProfile = profileUtils.findOrThrow(Objects.requireNonNull(friendUsername));
        if (isFriends(username, friendUsername)) {
            throw new AlreadyFriendsException(username, friendUsername);
        }
        profile.addFriend(friendProfile);
        profileRepository.save(profile);
    }

    public Collection<ProfileDto> findFriends(String username) {
        Profile profile = profileUtils.findOrThrow(Objects.requireNonNull(username));
        return profileMapper.mapToDtos(profile.getFriends());
    }

    @Override
    public boolean isFriends(String username, String friendUsername) {
        return profileUtils.isFriendsWith(username, friendUsername);
    }


    private void throwIfExists(String username) {
        if (profileRepository.findByUsername(username) != null) {
            throw new UsernameAlreadyExistsException(username);
        }
    }
}
