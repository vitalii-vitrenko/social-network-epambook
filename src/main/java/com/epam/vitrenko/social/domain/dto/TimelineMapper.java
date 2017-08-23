package com.epam.vitrenko.social.domain.dto;

import com.epam.vitrenko.social.domain.entity.Timeline;
import org.mapstruct.Mapper;

@Mapper(uses = ProfileMapper.class, componentModel = "spring")
public interface TimelineMapper {

    TimelineDto mapToDto(Timeline timeline);

    Timeline mapFromDto(TimelineDto timelineDto);
}
