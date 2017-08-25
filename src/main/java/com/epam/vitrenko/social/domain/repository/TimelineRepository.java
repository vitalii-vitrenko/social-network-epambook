package com.epam.vitrenko.social.domain.repository;

import com.epam.vitrenko.social.domain.entity.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {

    Collection<Timeline> findAllByOwnerUsername(String username);
}
