package com.epam.vitrenko.social.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Timeline {

    public static final int NOTE_TEXT_MAX_LENGTH = 128;

    @Column(nullable = false, length = NOTE_TEXT_MAX_LENGTH)
    private String noteText;

    @ManyToOne(optional = false)
    private Profile profile;

    @ManyToOne(optional = false)
    private Profile author;

    @Id
    private long id;
}
