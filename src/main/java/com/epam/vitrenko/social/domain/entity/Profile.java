package com.epam.vitrenko.social.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    public static final int NAME_MAX_LENGTH = 52;

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false, length = USERNAME_MAX_LENGTH)
    private String username;

    @Column(nullable = false, length = NAME_MAX_LENGTH)
    private String name;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "owner", cascade = ALL, orphanRemoval = true)
    private final Set<Timeline> timelines = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "friends", joinColumns = {
            @JoinColumn(name = "friend1", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "friend2", referencedColumnName = "id", nullable = false)})
    private final Set<Profile> friends = new HashSet<>();

}
