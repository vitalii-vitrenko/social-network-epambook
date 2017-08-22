package com.epam.vitrenko.social;

import com.epam.vitrenko.social.model.entity.Profile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = Profile.class)
public class SocialNetworkEpambookApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkEpambookApplication.class, args);
    }
}
