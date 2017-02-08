package org.ametiste.bootex.linkcenter.mvc;

import org.ametiste.lang.object.trace.MethodCallEvent;
import org.ametiste.lang.object.trace.MethodCallListener;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * @since
 */
public class SpringMVCMethodMappingExtractor implements MethodCallListener {

    private final Consumer<String> pathTemplateConsumer;

    private final AbstractHandlerMethodMapping mapping;

    public SpringMVCMethodMappingExtractor(AbstractHandlerMethodMapping mapping, Consumer<String> pathTemplateConsumer) {
        this.mapping = mapping;
        this.pathTemplateConsumer = pathTemplateConsumer;
    }

    @Override
    public void methodCalled(MethodCallEvent methodCallEvent) {

        final Method method = methodCallEvent.method();

        Map.Entry entry;

        try {
            entry = (Map.Entry) mapping.getHandlerMethods().entrySet().stream().filter(
                    (e) -> ((HandlerMethod) ((Map.Entry)e).getValue()).getMethod().equals(method)
            )
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        final RequestMappingInfo mappingInfo = (RequestMappingInfo) entry.getKey();

        mappingInfo
            .getPatternsCondition()
            .getPatterns()
            .forEach(pathTemplateConsumer::accept);

    }

}
