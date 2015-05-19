package org.ametiste.springframework.boot.smartcors;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>
 * Enables {@link SmartCORSFilter}, filter object can be configured using
 * properties defined by the {@link SmartCORSFilterProperties} class.
 * </p>
 *
 * @see SmartCORSFilter
 * @see SmartCORSFilterConfiguration
 * @see SmartCORSFilterProperties
 *
 * @since 0.1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Import(SmartCORSFilterConfiguration.class)
public @interface EnableSmartCORSFilter {

}
