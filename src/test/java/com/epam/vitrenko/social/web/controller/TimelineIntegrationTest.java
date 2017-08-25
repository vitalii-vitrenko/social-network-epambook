package com.epam.vitrenko.social.web.controller;

import com.epam.vitrenko.social.domain.dto.TimelineDto;
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
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class TimelineIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper jsonMapper;

    @Test
    public void canCreateTimeline() throws Exception {
        String username = "username1";
        TimelineDto timelineDto = TimelineDto.builder()
                .noteText("someNoteText")
                .build();
        String jsonTimeline = jsonMapper.writeValueAsString(timelineDto);

        mvc.perform(post("/users/{username}/timelines", username).contentType(MediaType.APPLICATION_JSON).content(jsonTimeline))
                .andDo(print())
                .andExpect(status().isNoContent());

        timelineDto.setAuthor(username);
        Map<String, String> expectedTimeline = jsonMapper.convertValue(timelineDto, Map.class);
        mvc.perform(get("/users/{username}/timelines", username))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timelines[0]").value(expectedTimeline));
    }
}
