package org.apache.ibatis.annotations;

import org.apache.ibatis.mapping.StatementType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that specify an SQL for retrieving a key value.
 *
 * <p>
 * <b>How to use:</b>
 *
 * <pre>
 * public interface UserMapper {
 *   &#064;SelectKey(statement = "SELECT identity('users')", keyProperty = "id", before = true, resultType = int.class)
 *   &#064;Insert("INSERT INTO users (id, name) VALUES(#{id}, #{name})")
 *   boolean insert(User user);
 * }
 * </pre>
 *
 * @author Clinton Begin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(SelectKey.List.class)
public @interface SelectKey {
    /**
     * Returns an SQL for retrieving a key value.
     *
     * @return an SQL for retrieving a key value
     */
    String[] statement();

    /**
     * Returns property names that holds a key value.
     * <p>
     * If you specify multiple property, please separate using comma(',').
     * </p>
     *
     * @return property names that separate with comma(',')
     */
    String keyProperty();

    /**
     * Returns column names that retrieves a key value.
     * <p>
     * If you specify multiple column, please separate using comma(',').
     * </p>
     *
     * @return column names that separate with comma(',')
     */
    String keyColumn() default "";

    /**
     * Returns whether retrieves a key value before executing insert/update statement.
     *
     * @return {@code true} if execute before; {@code false} if otherwise
     */
    boolean before();

    /**
     * Returns the key value type.
     *
     * @return the key value type
     */
    Class<?> resultType();

    /**
     * Returns the statement type to use.
     *
     * @return the statement type
     */
    StatementType statementType() default StatementType.PREPARED;

    /**
     * @return A database id that correspond this select key
     * @since 3.5.5
     */
    String databaseId() default "";

    /**
     * The container annotation for {@link SelectKey}.
     *
     * @author Kazuki Shimizu
     * @since 3.5.5
     */
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface List {
        SelectKey[] value();
    }

}
