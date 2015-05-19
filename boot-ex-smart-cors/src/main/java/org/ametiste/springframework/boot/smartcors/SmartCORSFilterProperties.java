package org.ametiste.springframework.boot.smartcors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *     Defines set of properties that would be used to configure {@link SmartCORSFilter} instance.
 * </p>
 *
 * <p>
 *     Defined properties are included:
 *
 *     <el>
 *         <li>ametiste.boot-ex.smart.cors.headers.<i>keyName</i> - map-like property defines set of
 *         header names and values.</li>
 *         <li>ametiste.boot-ex.smart.cors.headers.expandDefault - default value is true, if this property
 *         value is true than a filter will be created using composition of default and provided
 *         headers.</li>
 *     </el>
 * </p>
 *
 * <p>
 *     Note, this properties is designed to provide usable default configuration that provides common
 *     set of default headers, if a special configuration required, <i>expandDefault</i> property
 *     could be set to <i>false</i> and new set of headers could be build using
 *     <i>headers</i> map-like property.
 * </p>
 *
 */
@ConfigurationProperties("ametiste.boot-ex.smart.cors")
public class SmartCORSFilterProperties {

    /**
     * <p>
     *      Defines a header name and value.
     * </p>
     * <p>
     *      <b>NOTE</b>, only <i>access control</i> headers that prefixed as the <i>Access-Control-</i> are allowed
     *      to be used as <i>CORS</i> headers.
     * </p>
     *
     * @since 0.1.0
     */
    private Map<String, String> headers = new HashMap<>();

    /**
     * <p>
     *      If this property value is true, than configuration will expand the set of default properties.
     *      Note, default headers will be redefined by definitions given in a properties, if present.
     * </p>
     *
     * @since 0.1.0
     */
    private boolean expandDefault = true;

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public boolean isExpandDefault() {
        return expandDefault;
    }

    public void setExpandDefault(boolean expandDefault) {
        this.expandDefault = expandDefault;
    }

}
