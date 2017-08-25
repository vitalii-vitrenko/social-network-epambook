package com.epam.vitrenko.social.domain.dto;

import com.epam.vitrenko.social.domain.entity.Timeline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface TimelineMapper {

    @Mappings({
            @Mapping(target = "author", source = "timeline.author.username")
    })
    TimelineDto mapToDto(Timeline timeline);

    Collection<TimelineDto> mapToDtos(Collection<Timeline> timelines);

    @Mappings({
            @Mapping(target = "author", ignore = true)
    })
    Timeline mapFromDto(TimelineDto timelineDto);
}
