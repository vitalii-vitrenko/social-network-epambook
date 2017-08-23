package com.epam.vitrenko.social;

import com.epam.vitrenko.social.domain.entity.Profile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = Profile.class)
public class SocialNetworkEpambookApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkEpambookApplication.class, args);
    }
}
