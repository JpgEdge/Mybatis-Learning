package org.apache.ibatis.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that specify an SQL for updating record(s).
 *
 * <p>
 * <b>How to use:</b>
 *
 * <pre>
 * public interface UserMapper {
 *   &#064;Update("UPDATE users SET name = #{name} WHERE id = #{id}")
 *   boolean update(User user);
 * }
 * </pre>
 *
 * @author Clinton Begin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Update.List.class)
public @interface Update {
    /**
     * Returns an SQL for updating record(s).
     *
     * @return an SQL for updating record(s)
     */
    String[] value();

    /**
     * @return A database id that correspond this statement
     * @since 3.5.5
     */
    String databaseId() default "";

    /**
     * The container annotation for {@link Update}.
     *
     * @author Kazuki Shimizu
     * @since 3.5.5
     */
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface List {
        Update[] value();
    }

}
