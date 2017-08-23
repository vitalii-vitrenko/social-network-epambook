package com.epam.vitrenko.social.domain.dto;

import com.epam.vitrenko.social.domain.entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileDto mapToDto(Profile profile);

    Profile mapFromDto(ProfileDto profileDto);

}
