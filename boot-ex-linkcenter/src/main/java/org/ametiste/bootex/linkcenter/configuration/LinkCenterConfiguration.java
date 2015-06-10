package org.ametiste.bootex.linkcenter.configuration;

import org.ametiste.bootex.linkcenter.hosts.DirectHostResolver;
import org.ametiste.bootex.linkcenter.LinkCenter;
import org.ametiste.bootex.linkcenter.mvc.SpringMVCMappingControllerScaner;
import org.ametiste.bootex.linkcenter.mvc.SpringMVCURIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;

/**
 *
 * @since 0.1.0
 */
@Configuration
@EnableConfigurationProperties(LinkCenterProperties.class)
public class LinkCenterConfiguration {

    @Autowired
    // NOTE: this object used by the SpringMVCMappingControllerScaner to extract URI templates from
    // the registered mappings
    private AbstractHandlerMethodMapping mapping;

    @Autowired
    private LinkCenterProperties properties;

    @Bean
    public LinkCenter linkCenter() {
        return new LinkCenter(
            new SpringMVCMappingControllerScaner(mapping),
            new DirectHostResolver(properties.getDirectHostName()),
            () -> new SpringMVCURIBuilder()
        );
    }

}
