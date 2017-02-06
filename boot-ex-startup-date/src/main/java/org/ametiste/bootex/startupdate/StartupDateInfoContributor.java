package org.ametiste.bootex.startupdate;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;

import java.util.Collections;
import java.util.Date;

/**
 * {@code StartupDateInfoContributor} is {@link InfoContributor} implementation that provides information for info path
 * about application startup date and time.
 *
 * @since 0.1.1
 */
public class StartupDateInfoContributor implements InfoContributor {

    private final Date startupTime;

    public StartupDateInfoContributor() {
        this(new Date());
    }

    public StartupDateInfoContributor(Date startupTime) {
        if (startupTime == null) {
            throw new IllegalArgumentException("startup date must be initialized");
        }
        this.startupTime = startupTime;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("startup", Collections.singletonMap("date", startupTime.toString()));
    }
}
