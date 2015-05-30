package org.ametiste.bootex.linkcenter;

import java.net.URI;

/**
 *
 * @since
 */
public interface URIBuilder {

    URIBuilder host(String host);

    URIBuilder scheme(String scheme);

    URIBuilder path(String path);

    URI toURI();

    String toURITemplate();

}
