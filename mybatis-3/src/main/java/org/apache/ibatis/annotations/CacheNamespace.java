package org.apache.ibatis.annotations;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.LruCache;
import org.apache.ibatis.cache.impl.PerpetualCache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that specify to use cache on namespace(e.g. mapper interface).
 *
 * <p>
 * <b>How to use:</b>
 *
 * <pre>
 * &#064;CacheNamespace(implementation = CustomCache.class, properties = {
 *   &#064;Property(name = "host", value = "${mybatis.cache.host}"),
 *   &#064;Property(name = "port", value = "${mybatis.cache.port}"),
 *   &#064;Property(name = "name", value = "usersCache")
 * })
 * public interface UserMapper {
 *   // ...
 * }
 * </pre>
 *
 * @author Clinton Begin
 * @author Kazuki Shimizu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CacheNamespace {

    /**
     * Returns the cache implementation type to use.
     *
     * @return the cache implementation type
     */
    Class<? extends Cache> implementation() default PerpetualCache.class;

    /**
     * Returns the cache evicting implementation type to use.
     *
     * @return the cache evicting implementation type
     */
    Class<? extends Cache> eviction() default LruCache.class;

    /**
     * Returns the flush interval.
     *
     * @return the flush interval
     */
    long flushInterval() default 0;

    /**
     * Return the cache size.
     *
     * @return the cache size
     */
    int size() default 1024;

    /**
     * Returns whether use read/write cache.
     *
     * @return {@code true} if use read/write cache; {@code false} if otherwise
     */
    boolean readWrite() default true;

    /**
     * Returns whether block the cache at request time or not.
     *
     * @return {@code true} if block the cache; {@code false} if otherwise
     */
    boolean blocking() default false;

    /**
     * Returns property values for a implementation object.
     *
     * @return property values
     * @since 3.4.2
     */
    Property[] properties() default {};

}
