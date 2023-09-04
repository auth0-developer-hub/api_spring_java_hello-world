package com.example.helloworld.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import lombok.Value;

@Value
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    String clientOriginUrl;

    @ConstructorBinding
    public ApplicationProperties(final String clientOriginUrl) {
        this.clientOriginUrl = clientOriginUrl;
    }

}
