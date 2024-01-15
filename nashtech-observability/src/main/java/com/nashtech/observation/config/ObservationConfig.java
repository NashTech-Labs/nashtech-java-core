package com.nashtech.observation.config;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObservationConfig {

    /**
     * Register this bean for observed aspect.
     * @param observationRegistry
     * @return ObservedAspect
     */
    @Bean
    ObservedAspect observedAspect(
            final ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }
}
