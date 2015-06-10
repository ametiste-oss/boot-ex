package org.ametiste.bootex.linkcenter.configuration;

import org.ametiste.bootex.linkcenter.hosts.DirectHostResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 *  Defines base set of properties that can be used to customize {@code LinkCenter} behaviour.
 * </p>
 *
 * @since 0.1.1
 */
@ConfigurationProperties("ametiste.boot-ex.linkCenter")
public class LinkCenterProperties {

    /**
     * <p>
     *     Defines host name that would be resolved by default {@link DirectHostResolver} instance
     *     as hostname for URIs.
     * </p>
     *
     * <p>
     *     Note, by default this property is set to '127.0.0.1'.
     * </p>
     *
     */
    private String directHostName = "127.0.0.1";

    public String getDirectHostName() {
        return directHostName;
    }

    public void setDirectHostName(String directHostName) {
        this.directHostName = directHostName;
    }
}
