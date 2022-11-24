package org.apache.ibatis.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that be grouping mapping definitions for constructor.
 *
 * <p>
 * <b>How to use:</b>
 *
 * <pre>
 * public interface UserMapper {
 *   &#064;ConstructorArgs({
 *     &#064;Arg(column = "id", javaType = int.class, id = true),
 *     &#064;Arg(column = "name", javaType = String.class),
 *     &#064;Arg(javaType = UserEmail.class, select = "selectUserEmailById", column = "id")
 *   })
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
public @interface ConstructorArgs {
    /**
     * Returns mapping definitions for constructor.
     *
     * @return mapping definitions
     */
    Arg[] value() default {};
}
