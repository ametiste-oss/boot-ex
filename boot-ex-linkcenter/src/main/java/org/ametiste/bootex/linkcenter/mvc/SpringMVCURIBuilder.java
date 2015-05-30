package org.ametiste.bootex.linkcenter.mvc;

import org.ametiste.bootex.linkcenter.URIBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 *
 * @since 0.1.0
 */
public class SpringMVCURIBuilder implements URIBuilder {

    private UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

    @Override
    public URIBuilder host(String host) {
        uriComponentsBuilder.host(host);
        return this;
    }

    @Override
    public URIBuilder scheme(String scheme) {
        uriComponentsBuilder.scheme(scheme);
        return this;
    }

    @Override
    public URIBuilder path(String path) {
        uriComponentsBuilder.path(path);
        return this;
    }

    @Override
    public URI toURI() {
        return uriComponentsBuilder.build().toUri();
    }

    @Override
    public String toURITemplate() {
        return uriComponentsBuilder.build().toUriString();
    }

}
