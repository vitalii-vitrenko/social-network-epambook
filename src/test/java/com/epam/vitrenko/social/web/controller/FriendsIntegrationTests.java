package com.epam.vitrenko.social.web.controller;

import com.epam.vitrenko.social.domain.dto.ProfileDto;
import com.epam.vitrenko.social.domain.dto.TimelineDto;
import com.epam.vitrenko.social.domain.service.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class FriendsIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper jsonMapper;

    @Test
    public void canAddFriends() throws Exception {
        String username = "username1";
        ProfileDto friend = ProfileDto.builder()
                .username("username2")
                .name("name2")
                .dateOfBirth(LocalDate.of(2015, 8, 8))
                .build();
        String jsonUsername = jsonMapper.writeValueAsString(Collections.singletonMap("username", friend.getUsername()));

        mvc.perform(post("/users/{username}/friends", username).contentType(APPLICATION_JSON).content(jsonUsername))
                .andExpect(status().isNoContent());

        Map<String, Object> jsonFriend = jsonMapper.convertValue(friend, Map.class);
        mvc.perform(get("/users/{username}/friends", username).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.friends[0]").value(jsonFriend));
    }

    @Test
    public void canAddTimelineToFriend() throws Exception {
        String friendUsername1 = "friend1";
        String friendUsername2 = "friend2";
        TimelineDto timelineDto = TimelineDto.builder()
                .noteText("someNoteText")
                .build();
        String jsonTimeline = jsonMapper.writeValueAsString(timelineDto);

        mvc.perform(post("/users/{username1}/friends/{username2}/timelines", friendUsername1, friendUsername2)
                .contentType(MediaType.APPLICATION_JSON).content(jsonTimeline))
                .andExpect(status().isNoContent());

        timelineDto.setAuthor(friendUsername1);
        Map<String, String> expectedTimeline = jsonMapper.convertValue(timelineDto, Map.class);
        mvc.perform(get("/users/{username1}/friends/{username2}/timelines", friendUsername1, friendUsername2)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timelines[0]").value(expectedTimeline));

    }
}
