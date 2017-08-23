package com.epam.vitrenko.social.domain.repository;

import com.epam.vitrenko.social.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByUsername(String username);
}
