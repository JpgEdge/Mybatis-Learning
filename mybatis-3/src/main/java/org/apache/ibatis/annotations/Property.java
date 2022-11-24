package org.apache.ibatis.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that inject a property value.
 *
 * @author Kazuki Shimizu
 * @see CacheNamespace
 * @since 3.4.2
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Property {

    /**
     * Returns the property name.
     *
     * @return the property name
     */
    String name();

    /**
     * Returns the property value or placeholder.
     *
     * @return the property value or placeholder
     */
    String value();
}
