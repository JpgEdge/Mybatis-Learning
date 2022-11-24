package org.apache.ibatis.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that conditional mapping definition for {@link TypeDiscriminator}.
 *
 * @author Clinton Begin
 * @see TypeDiscriminator
 * @see Result
 * @see Arg
 * @see Results
 * @see ConstructorArgs
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Case {

    /**
     * Return the condition value to apply this mapping.
     *
     * @return the condition value
     */
    String value();

    /**
     * Return the object type that create a object using this mapping.
     *
     * @return the object type
     */
    Class<?> type();

    /**
     * Return mapping definitions for property.
     *
     * @return mapping definitions for property
     */
    Result[] results() default {};

    /**
     * Return mapping definitions for constructor.
     *
     * @return mapping definitions for constructor
     */
    Arg[] constructArgs() default {};

}
