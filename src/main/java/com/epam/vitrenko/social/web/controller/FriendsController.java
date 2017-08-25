package com.epam.vitrenko.social.web.controller;

import com.epam.vitrenko.social.domain.dto.ProfileDto;
import com.epam.vitrenko.social.domain.dto.TimelineDto;
import com.epam.vitrenko.social.domain.service.NotFriendsException;
import com.epam.vitrenko.social.domain.service.ProfileService;
import com.epam.vitrenko.social.domain.service.TimelineService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/users/{username}/friends")
@AllArgsConstructor
public class FriendsController {

    private final ProfileService profileService;

    private final TimelineService timelineService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addFriend(@PathVariable String username, @RequestBody Map<String, String> requestBody) {
        String friendUsername = requestBody.get("username");
        profileService.makeFriends(username, friendUsername);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Collection<ProfileDto>> findFriends(@PathVariable String username) {
        return Collections.singletonMap("friends", profileService.findFriends(username));
    }

    @RequestMapping(path = "/{friendUsername}/timelines", method = RequestMethod.GET)
    public Map<String, Collection<TimelineDto>> findFriendTimelines(@PathVariable String username,
                                                                    @PathVariable String friendUsername) {

        if (!profileService.isFriends(username, friendUsername)) {
            throw new NotFriendsException(username, friendUsername);
        }
        Map<String, Collection<TimelineDto>> result = Collections.singletonMap("timelines", timelineService.findAllByUsername(friendUsername));
        return result;
    }

    @RequestMapping(path = "/{friendUsername}/timelines", method = RequestMethod.POST)
    public ResponseEntity<?> addTimeline(@RequestBody @Valid TimelineDto timelineDto,
                                         @PathVariable String username,
                                         @PathVariable String friendUsername) {
        timelineDto.setAuthor(username);
        timelineService.addTimelineToFriend(timelineDto, friendUsername);
        return ResponseEntity.noContent().build();
    }
}
