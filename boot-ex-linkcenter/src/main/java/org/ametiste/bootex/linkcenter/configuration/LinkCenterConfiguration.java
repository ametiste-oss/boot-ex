package org.ametiste.bootex.linkcenter.configuration;

import org.ametiste.bootex.linkcenter.DirectHostResolver;
import org.ametiste.bootex.linkcenter.LinkCenter;
import org.ametiste.bootex.linkcenter.mvc.SpringMVCMappingControllerScaner;
import org.ametiste.bootex.linkcenter.mvc.SpringMVCURIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;

/**
 *
 * @since 0.1.0
 */
@Configuration
public class LinkCenterConfiguration {

    @Autowired
    private AbstractHandlerMethodMapping mapping;

    @Bean
    public LinkCenter linkCenter() {
        return new LinkCenter(
            new SpringMVCMappingControllerScaner(mapping),
            new DirectHostResolver("dph-dmr-sro"),
            () -> new SpringMVCURIBuilder()
        );
    }

}
