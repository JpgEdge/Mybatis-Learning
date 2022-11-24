package org.apache.ibatis.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation that specify result map names to use.
 *
 * <p>
 * <b>How to use:</b><br>
 * Mapper interface:
 *
 * <pre>
 * public interface UserMapper {
 *   &#064;Select("SELECT id, name FROM users WHERE id = #{id}")
 *   &#064;ResultMap("userMap")
 *   User selectById(int id);
 *
 *   &#064;Select("SELECT u.id, u.name FROM users u INNER JOIN users_email ue ON u.id = ue.id WHERE ue.email = #{email}")
 *   &#064;ResultMap("userMap")
 *   User selectByEmail(String email);
 * }
 * </pre>
 * Mapper XML:
 * <pre>{@code
 * <mapper namespace="com.example.mapper.UserMapper">
 *   <resultMap id="userMap" type="com.example.model.User">
 *     <id property="id" column="id" />
 *     <result property="name" column="name" />
 *     <association property="email" select="selectUserEmailById" column="id" fetchType="lazy"/>
 *   </resultMap>
 * </mapper>
 * }
 * </pre>
 *
 * @author Jeff Butler
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ResultMap {
    /**
     * Returns result map names to use.
     *
     * @return result map names
     */
    String[] value();
}
