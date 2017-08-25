package com.epam.vitrenko.social.domain.service;

import com.epam.vitrenko.social.domain.dto.TimelineDto;
import com.epam.vitrenko.social.domain.dto.TimelineMapper;
import com.epam.vitrenko.social.domain.entity.Profile;
import com.epam.vitrenko.social.domain.entity.Timeline;
import com.epam.vitrenko.social.domain.repository.TimelineRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

import static java.util.Objects.requireNonNull;

@Service
@Transactional
@AllArgsConstructor
public class TimelineServiceImpl implements TimelineService {

    private final ProfileUtils profileUtils;

    private final TimelineMapper timelineMapper;

    private final TimelineRepository timelineRepository;

    @Override
    public void addTimelineToAuthor(TimelineDto timelineDto) {
        Profile author = profileUtils.findOrThrow(requireNonNull(timelineDto).getAuthor());
        addTimeline(timelineDto, author, author);
    }

    @Override
    public void addTimelineToFriend(TimelineDto timelineDto, String friendUsername) {
        Profile owner = profileUtils.findOrThrow(requireNonNull(friendUsername));
        Profile author = profileUtils.findOrThrow(requireNonNull(timelineDto).getAuthor());
        if (!profileUtils.isFriendsWith(author.getUsername(), owner.getUsername())) {
            throw new NotFriendsException(author.getUsername(), owner.getUsername());
        }
        addTimeline(timelineDto, owner, author);
    }

    @Override
    public Collection<TimelineDto> findAllByUsername(String username) {
        Profile profile = profileUtils.findOrThrow(requireNonNull(username));
        return timelineMapper.mapToDtos(profile.getTimelines());
    }

    void addTimeline(TimelineDto timelineDto, Profile owner, Profile author) {
        Timeline timeline = timelineMapper.mapFromDto(timelineDto);
        timeline.setAuthor(author);
        timeline.setOwner(owner);
        timelineRepository.save(timeline);
    }

}
