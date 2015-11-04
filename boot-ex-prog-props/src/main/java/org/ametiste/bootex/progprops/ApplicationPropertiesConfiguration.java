package org.ametiste.bootex.progprops;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>
 *     Allows to hook application environment configuration and provide additional application properties
 *     programmaticaly.
 * </p>
 *
 * <p>
 *     Note, to hook bootstrap by this configuration, META-INF/spring.factories should be presented
 *     in the using module classpath.
 * </p>
 *
 * @since 0.1.1
 */
public abstract class ApplicationPropertiesConfiguration implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    public final static String SOURCE_NAME = "boot-ex-prog-properties";

    private Map<String, String> props = new HashMap<>();

    protected final void defProperty(String name, String value) {
        props.put(name, value);
    }

    protected void configureProperties() {

    }

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        configureProperties();
        final ConfigurableEnvironment environment = event.getEnvironment();
        Properties properties = new Properties();
        properties.putAll(props);
        environment.getPropertySources().addFirst(
                new PropertiesPropertySource(SOURCE_NAME, properties));
    }

}
