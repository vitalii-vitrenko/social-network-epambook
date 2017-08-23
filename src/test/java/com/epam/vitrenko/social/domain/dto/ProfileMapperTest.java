package com.epam.vitrenko.social.domain.dto;


import com.epam.vitrenko.social.domain.entity.Profile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DtoTestConfiguration.class)
public class ProfileMapperTest {

    @Autowired
    ProfileMapper profileMapper;

    private String username = "someUsername";

    private String name = "someName";

    private LocalDate dateOfBirth = LocalDate.now();

    @Test
    public void shouldMapProfileToDto() {
        Profile profile = Profile.builder()
                .id(1L)
                .username(username)
                .name(name)
                .dateOfBirth(dateOfBirth)
                .build();

        ProfileDto profileDto = profileMapper.mapToDto(profile);

        assertThat(profileDto).hasNoNullFieldsOrProperties();
        assertThat(profileDto.getName()).as("name").isEqualTo(name);
        assertThat(profileDto.getUsername()).as("username").isEqualTo(username);
        assertThat(profileDto.getDateOfBirth()).as("dateOfBirth").isEqualTo(dateOfBirth);
    }

    @Test
    public void shouldMapDtoToProfile() {
        ProfileDto profileDto = ProfileDto.builder()
                .username(username)
                .name(name)
                .dateOfBirth(dateOfBirth)
                .build();

        Profile profile = profileMapper.mapFromDto(profileDto);

        assertThat(profile).hasNoNullFieldsOrPropertiesExcept("id");
        assertThat(profile.getName()).as("name").isEqualTo(name);
        assertThat(profile.getUsername()).as("username").isEqualTo(username);
        assertThat(profile.getDateOfBirth()).as("dateOfBirth").isEqualTo(dateOfBirth);
    }

}
