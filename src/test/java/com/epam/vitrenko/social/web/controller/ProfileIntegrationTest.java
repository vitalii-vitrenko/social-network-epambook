package com.epam.vitrenko.social.web.controller;

import com.epam.vitrenko.social.domain.dto.ProfileDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class ProfileIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ObjectMapper jsonMapper;

    @Test
    public void canCreateProfile() throws Exception {
        ProfileDto profileDto = ProfileDto.builder()
                .username("someUsername")
                .name("someName")
                .dateOfBirth(LocalDate.now())
                .build();
        String jsonProfile = jsonMapper.writeValueAsString(profileDto);

        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(jsonProfile))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("**/users/" + profileDto.getUsername()));

        mvc.perform(get("/users/" + profileDto.getUsername()))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonProfile));
    }

}
