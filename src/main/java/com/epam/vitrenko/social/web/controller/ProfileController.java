package com.epam.vitrenko.social.web.controller;

import com.epam.vitrenko.social.domain.dto.ProfileDto;
import com.epam.vitrenko.social.domain.service.ProfileService;
import com.epam.vitrenko.social.domain.service.UsernameNotExist;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.swing.*;
import javax.validation.Valid;
import java.net.URI;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class ProfileController {

    private ProfileService profileService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody @Valid ProfileDto newProfile) {
        profileService.createProfile(newProfile);

        URI uri = MvcUriComponentsBuilder
                .fromMethodCall(on(ProfileController.class).findUserByUsername(newProfile.getUsername()))
                .build().encode().toUri();
        return ResponseEntity.created(uri).build();
    }



    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ProfileDto findUserByUsername(@PathVariable String username) {
        return profileService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotExist(username));
    }

}
