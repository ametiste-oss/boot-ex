package org.ametiste.bootex.startupdate.configuration;

import org.ametiste.bootex.startupdate.StartupDateInfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@code StartupDateInfoConfiguration} provides {@link org.springframework.boot.actuate.info.InfoContributor} bean
 * that adds application startup date information to {@literal info} endpoint.
 *
 * @since 0.1.1
 */
@Configuration
public class StartupDateInfoConfiguration {

    @Bean
    public StartupDateInfoContributor startupDateInfoContributor() {
        return new StartupDateInfoContributor();
    }
}
