package org.ametiste.bootex.linkcenter.mvc;

import org.ametiste.bootex.linkcenter.ControllerScaner;
import org.amtetiste.uttil.object.trace.CallsTraceScaner;
import org.amtetiste.uttil.object.trace.Trace;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * <p>
 *     {@link ControllerScaner} implementations that uses {@link SpringMVCMethodMappingExtractor} and
 *     {@link CallsTraceScaner} to trace path to and extract mapping of a controller methods.
 * </p>
 *
 * <p>
 *     Note, this implementation supports only {@link Controller} or {@link RestController} objects.
 * </p>
 *
 * @since 0.1.0
 */
public class SpringMVCMappingControllerScaner implements ControllerScaner {

    private final AbstractHandlerMethodMapping mapping;

    public SpringMVCMappingControllerScaner(AbstractHandlerMethodMapping mapping) {
        Assert.notNull(mapping, "mapping must be not null");
        this.mapping = mapping;
    }

    public  <T> Trace<T> traceController(Class<T> controllerClass, Consumer<String> tracedPathConsumer) {

        if (isNotSpringMvcController(controllerClass)) {
            throw new IllegalArgumentException("This scaner instance should " +
                    "be used only to trace @Controller/@RestController objects.");
        }

        final CallsTraceScaner<T> callsTraceScaner = new CallsTraceScaner<>(controllerClass,
            Arrays.asList(new SpringMVCMethodMappingExtractor(mapping, tracedPathConsumer::accept))
        );

        return callsTraceScaner.createTrace();
    }

    private <T> boolean isNotSpringMvcController(Class<T> tClass) {
        return !tClass.isAnnotationPresent(Controller.class) &&
                !tClass.isAnnotationPresent(RestController.class);
    }

}
