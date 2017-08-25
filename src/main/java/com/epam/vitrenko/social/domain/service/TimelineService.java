package com.epam.vitrenko.social.domain.service;

import com.epam.vitrenko.social.domain.dto.TimelineDto;

import java.util.Collection;

public interface TimelineService {

    void addTimelineToAuthor(TimelineDto timelineDto);

    void addTimelineToFriend(TimelineDto timelineDto, String friendUsername);

    Collection<TimelineDto> findAllByUsername(String username);
}
