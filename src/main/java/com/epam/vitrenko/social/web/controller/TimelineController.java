package com.epam.vitrenko.social.web.controller;

import com.epam.vitrenko.social.domain.dto.TimelineDto;
import com.epam.vitrenko.social.domain.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users/{username}/timelines")
public class TimelineController {

    private TimelineService timelineService;

    @Autowired
    public TimelineController(TimelineService timelineService) {
        this.timelineService = Objects.requireNonNull(timelineService);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addTimeline(@RequestBody @Valid TimelineDto timelineDto,
                                         @PathVariable String username) {
        timelineDto.setAuthor(username);
        timelineService.addTimelineToAuthor(timelineDto);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Collection<TimelineDto>> findTimelines(@PathVariable String username) {
        Collection<TimelineDto> timelines = timelineService.findAllByUsername(username);
        return Collections.singletonMap("timelines", timelines);
    }

}
