package org.apache.ibatis.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that specify an SQL for retrieving record(s).
 *
 * <p>
 * <b>How to use:</b>
 *
 * <pre>
 * public interface UserMapper {
 *   &#064;Select("SELECT id, name FROM users WHERE id = #{id}")
 *   User selectById(int id);
 * }
 * </pre>
 *
 * @author Clinton Begin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Select.List.class)
public @interface Select {
    /**
     * Returns an SQL for retrieving record(s).
     *
     * @return an SQL for retrieving record(s)
     */
    String[] value();

    /**
     * @return A database id that correspond this statement
     * @since 3.5.5
     */
    String databaseId() default "";

    /**
     * The container annotation for {@link Select}.
     *
     * @author Kazuki Shimizu
     * @since 3.5.5
     */
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface List {
        Select[] value();
    }

}
