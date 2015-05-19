package org.ametiste.springframework.boot.smartcors;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class SmartCORSFilter implements Filter {

    private final Map<String, String> headers;

    // NOTE: .toLowerCase() explicit call used to stress case-insensitivity
    private final static String ACCESS_CONTROL_HEADER_PREFIX = "Access-Control-".toLowerCase();

    public SmartCORSFilter(Map<String, String> headers) {

        Assert.notNull(headers, "headers parameter can't be null");
        Assert.isTrue(corsHeaders(headers), "only headers prefied by 'Access-Control-' are allowed");

        this.headers = headers;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        headers.forEach(response::addHeader);
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

    private static boolean accessControlHeader(String headerName) {
        return headerName.toLowerCase().startsWith(ACCESS_CONTROL_HEADER_PREFIX);
    }

    /**
     * <p>
     * Checks is there are only allowed <i>cors</i> headres.
     * </p>
     * <p>
     * Note, this method allows to use empty header names set.
     * </p>
     */
    private static boolean corsHeaders(Map<String, String> headers) {

        final Set<String> headerNames = headers.keySet();

        if (headerNames.isEmpty()) {
            // NOTE: allow to use empty headers configuration without any exceptions
            return true;
        }

        return headerNames.stream().allMatch(SmartCORSFilter::accessControlHeader);
    }

}