package org.apache.ibatis.annotations;

import org.apache.ibatis.scripting.LanguageDriver;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that specify a {@link LanguageDriver} to use.
 *
 * <p>
 * <b>How to use:</b>
 *
 * <pre>
 * public interface UserMapper {
 *   &#064;Lang(MyXMLLanguageDriver.class)
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
public @interface Lang {
    /**
     * Returns the {@link LanguageDriver} implementation type to use.
     *
     * @return the {@link LanguageDriver} implementation type
     */
    Class<? extends LanguageDriver> value();
}
