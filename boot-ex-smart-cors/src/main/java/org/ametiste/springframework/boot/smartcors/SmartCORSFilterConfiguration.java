package org.ametiste.springframework.boot.smartcors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *     Enables {@link SmartCORSFilter}, filter object can be configured using
 *     properties defined by the {@link SmartCORSFilterProperties} class.
 * </p>
 *
 * <p>
 *     With the default configuration, {@code SmartCORSFilter} would apply next headers:
 *
 *     <el>
 *         <li>Access-Control-Allow-Origin : *</li>
 *         <li>Access-Control-Allow-Methods : POST, GET, OPTIONS, DELETE</li>
 *         <li>Access-Control-Max-Age : 3600</li>
 *         <li>Access-Control-Allow-Headers : x-requested-with, content-type, user-agent</li>
 *     </el>
 *
 * </p>
 *
 * @see SmartCORSFilterProperties
 * @since 0.1.0
 */
@ComponentScan("org.ametiste.springframework.boot.smartcors")
@EnableConfigurationProperties(SmartCORSFilterProperties.class)
public class SmartCORSFilterConfiguration {

    private Map<String, String> defaultHeaders = new HashMap<>();

    {
        defaultHeaders.put("Access-Control-Allow-Origin", "*");
        defaultHeaders.put("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        defaultHeaders.put("Access-Control-Max-Age", "3600");
        defaultHeaders.put("Access-Control-Allow-Headers", "x-requested-with, content-type, user-agent");
    }

    @Autowired
    private SmartCORSFilterProperties properties;

    @Bean
    public SmartCORSFilter smartCORSFilter() {

        final Map<String, String> effectiveHeaders;

        if (properties.isExpandDefault()) {
            effectiveHeaders = new HashMap<>();
            effectiveHeaders.putAll(defaultHeaders);
            effectiveHeaders.putAll(properties.getHeaders());
        } else {
            effectiveHeaders = properties.getHeaders();
        }

        return new SmartCORSFilter(effectiveHeaders);

    }

}
