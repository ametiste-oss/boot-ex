package org.ametiste.bootex.linkcenter;

import org.amtetiste.uttil.object.trace.Trace;

import java.util.function.Consumer;

/**
 * <p>
 *     Object scaner that allows to scan {@code Controller} objects calls traces
 *     to mapped {@code URI template}.
 * </p>
 *
 * <p>
 * Usage example:
 * <br>
 * <pre>
 *
 * &#64RequestMapping("/hello")
 * class HelloController {
 *
 *      &#64RequestMapping("/world/{greetings}")
 *      public String world(&#64PathVariable("greetings") String greetingsVar) { ... }
 *
 * }
 *
 * ...
 *
 * scaner = new ControllerScaner { ... }
 * scaner
 *    .traceController(HelloController.class,
 *          (s) -> System.out.println("URI Template mapped to method is: " +s)
 *    )
 *    .recordTrace(
 *          (c) -> c.world(TraceParam.any())
 *    )
 * </pre>
 * <br>
 *     Will print to stdout: <i>URI Template mapped to method is: /hello/world/{greetings}</i>
 * </p>
 *
 * @since 0.1.0
 */
public interface ControllerScaner {

    /**
     * <p>
     *     Creates call trace for the given controller class, after trace recorded, a path traced
     *     during scaning will be provided to the given {@code tracedPathConsumer}.
     * </p>
     *
     * @param controllerClass controller class to trace
     * @param tracedPathConsumer traced URI template consumer
     * @param <T> type of a given controller class
     *
     * @return {@link Trace} object, that should be used by a client to record trace path in.
     */
    <T> Trace<T> traceController(Class<T> controllerClass, Consumer<String> tracedPathConsumer);

}
