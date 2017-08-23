package com.epam.vitrenko.social.domain.dto;

import com.epam.vitrenko.social.domain.entity.Profile;
import com.epam.vitrenko.social.domain.entity.Timeline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DtoTestConfiguration.class)
public class TimelineMapperTest {

    @Autowired
    private TimelineMapper timelineMapper;

    @Autowired
    private ProfileMapper profileMapper;

    private Profile author = Profile.builder()
            .id(1L)
            .username("authorUsername")
            .name("authorNome")
            .dateOfBirth(LocalDate.now())
            .build();

    private ProfileDto authorDto;

    @Before
    public void setUp() {
        authorDto = profileMapper.mapToDto(author);
    }

    @Test
    public void shouldMapTimelineToDto() {
        Timeline timeline = Timeline.builder()
                .id(2L)
                .noteText("someText")
                .author(author)
                .build();

        TimelineDto timelineDto = timelineMapper.mapToDto(timeline);

        assertThat(timelineDto).hasNoNullFieldsOrProperties();
        assertThat(timelineDto.getNoteText()).as("noteText").isEqualTo(timeline.getNoteText());
        assertThat(timelineDto.getAuthor()).as("author").isEqualTo(authorDto);
    }

    @Test
    public void shouldMapDtoToTimeline() {
        TimelineDto timelineDto = TimelineDto.builder()
                .author(authorDto)
                .noteText("someText")
                .build();

        Timeline timeline = timelineMapper.mapFromDto(timelineDto);

        assertThat(timeline.getAuthor()).isNotNull();
        assertThat(timeline.getNoteText()).isEqualTo(timelineDto.getNoteText());
    }

}
