package org.apache.ibatis.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that specify the property name(or column name) for a key value of {@link java.util.Map}.
 *
 * <p>
 * <b>How to use:</b>
 *
 * <pre>
 * public interface UserMapper {
 *   &#064;MapKey("id")
 *   &#064;Select("SELECT id, name FROM users WHERE name LIKE #{name} || '%")
 *   Map&lt;Integer, User&gt; selectByStartingWithName(String name);
 * }
 * </pre>
 *
 * @author Clinton Begin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MapKey {
    /**
     * Returns the property name(or column name) for a key value of {@link java.util.Map}.
     *
     * @return the property name(or column name)
     */
    String value();
}
