package com.epam.vitrenko.social.domain.dto;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ProfileMapper.class)
public class DtoTestConfiguration {
}
