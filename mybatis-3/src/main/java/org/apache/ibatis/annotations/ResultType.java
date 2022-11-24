package org.apache.ibatis.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation can be used when a @Select method is using a
 * ResultHandler.  Those methods must have void return type, so
 * this annotation can be used to tell MyBatis what kind of object
 * it should build for each row.
 *
 * <p>
 * <b>How to use:</b>
 *
 * <pre>
 * public interface UserMapper {
 *   &#064;ResultType(User.class)
 *   &#064;Select("SELECT id, name FROM users WHERE name LIKE #{name} || '%' ORDER BY id")
 *   void collectByStartingWithName(String name, ResultHandler&lt;User&gt; handler);
 * }
 * </pre>
 *
 * @author Jeff Butler
 * @since 3.2.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ResultType {
    /**
     * Returns the return type.
     *
     * @return the return type
     */
    Class<?> value();
}
