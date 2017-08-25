package com.epam.vitrenko.social.domain.dto;

import com.epam.vitrenko.social.domain.entity.Profile;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileDto mapToDto(Profile profile);

    Profile mapFromDto(ProfileDto profileDto);

    Collection<ProfileDto> mapToDtos (Collection<Profile> profiles);

}
