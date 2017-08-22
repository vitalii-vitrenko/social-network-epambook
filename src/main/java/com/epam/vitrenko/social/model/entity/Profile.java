package com.epam.vitrenko.social.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    public static final int USERNAME_MAX_LENGTH = 32;

    @Id
    private long id;

    @Column(unique = true, nullable = false, length = USERNAME_MAX_LENGTH)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Builder.Default
    @OneToMany(mappedBy = "profile", cascade = ALL, orphanRemoval = true)
    private Set<Timeline> timelines = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "friends", joinColumns = {
            @JoinColumn(name = "friend1", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "friend2", referencedColumnName = "id", nullable = false)})
    private Set<Profile> friends = new HashSet<>();

}
