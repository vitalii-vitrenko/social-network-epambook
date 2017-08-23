package com.epam.vitrenko.social.domain.dto;

import com.epam.vitrenko.social.domain.entity.Timeline;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimelineDto {

    public static final int NOTE_TEXT_MIN_LENGTH = 1;

    @NotNull
    @Size(min = NOTE_TEXT_MIN_LENGTH, max = Timeline.NOTE_TEXT_MAX_LENGTH)
    private String noteText;

    @NotNull
    private ProfileDto author;
}
