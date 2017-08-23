package com.epam.vitrenko.social.domain.dto;

import com.epam.vitrenko.social.domain.entity.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {

    public static final int USERNAME_MIN_LENGTH = 3;

    public static final int NAME_MIN_LENGTH = 3;

    @NotNull
    @Size(min = USERNAME_MIN_LENGTH, max = Profile.USERNAME_MAX_LENGTH)
    private String username;

    @NotNull
    @Size(min = NAME_MIN_LENGTH, max = Profile.NAME_MAX_LENGTH)
    private String name;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

}


