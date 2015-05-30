package org.ametiste.bootex.linkcenter;

/**
 *
 * <p>
 *     Simple host resolver, provides a value that is specificied during resolving construction.
 * </p>
 *
 * <p>
 *     This resolver may be used in the environment where host names are well known and
 *     don't have dynamic nature.
 * </p>
 *
 * @since 0.1.0
 */
public class DirectHostResolver implements HostResolver {

    private final String hostName;

    public DirectHostResolver(String hostName) {

        if (hostName == null || hostName.isEmpty()) {
            throw new IllegalArgumentException("hostName must be not null nor empty.");
        }

        this.hostName = hostName;
    }

    @Override
    public String resolveHostName() {
        return hostName;
    }

}
