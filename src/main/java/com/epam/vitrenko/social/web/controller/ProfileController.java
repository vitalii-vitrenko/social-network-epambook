package com.epam.vitrenko.social.web.controller;

import com.epam.vitrenko.social.domain.dto.ProfileDto;
import com.epam.vitrenko.social.domain.entity.Profile;
import com.epam.vitrenko.social.domain.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;
import java.util.Optional;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/users")
public class ProfileController {

    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody @Valid ProfileDto newProfile) {
        profileService.createProfile(newProfile);
        URI uri = MvcUriComponentsBuilder
                .fromMethodCall(on(ProfileController.class).findUserByUsername(newProfile.getUsername()))
                .build()
                .encode()
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping("/{username}")
    public ResponseEntity<ProfileDto> findUserByUsername(@PathVariable String username) {
        return Optional
                .ofNullable(profileService.findByUsername(username))
                .map(profile -> ResponseEntity.ok().body(profile))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
