package com.epam.vitrenko.social.domain.repository;

import com.epam.vitrenko.social.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByUsername(String username);

    @Query("SELECT (count(p) > 0) FROM Profile p join p.friends f where p.username = ?1 AND f.username = ?2")
    boolean isFriends(String username, String friendUsername);
}
