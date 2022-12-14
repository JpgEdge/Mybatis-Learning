package org.apache.ibatis.reflection.factory;

import org.apache.ibatis.reflection.ReflectionException;
import org.apache.ibatis.reflection.Reflector;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author Clinton Begin
 */
public class DefaultObjectFactory implements ObjectFactory, Serializable {

    private static final long serialVersionUID = -8855120656740914948L;

    @Override
    public <T> T create(Class<T> type) {
        return create(type, null, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        Class<?> classToCreate = resolveInterface(type);
        // we know types are assignable
        return (T) instantiateClass(classToCreate, constructorArgTypes, constructorArgs);
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return Collection.class.isAssignableFrom(type);
    }

    // 判断要创建的目标对象的类型，即如果传入的是接口则给出它的一种实现
    protected Class<?> resolveInterface(Class<?> type) {
        Class<?> classToCreate;
        if (type == List.class || type == Collection.class || type == Iterable.class) {
            classToCreate = ArrayList.class;
        } else if (type == Map.class) {
            classToCreate = HashMap.class;
        } else if (type == SortedSet.class) { // issue #510 Collections Support
            classToCreate = TreeSet.class;
        } else if (type == Set.class) {
            classToCreate = HashSet.class;
        } else {
            classToCreate = type;
        }
        return classToCreate;
    }

    /**
     * 创建类的实例
     * @param type 要创建实例的类
     * @param constructorArgTypes 构造方法输入参数类型
     * @param constructorArgs 构造方法输入参数
     * @return 创建的实例
     * @param <T> 实例参数
     */
    private <T> T instantiateClass(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        try {
            // 构造方法
            Constructor<T> constructor;
            // 参数类型列表为null或者参数类型为null
            if (constructorArgTypes == null || constructorArgs == null) {
                // 因此获取无参构造函数
                constructor = type.getDeclaredConstructor();
                try {
                    // 使用无参构造函数创建对象
                    return constructor.newInstance();
                } catch (IllegalAccessException e) {
                    // 如果发生异常,则修改构造函数的访问属性后再次尝试
                    if (Reflector.canControlMemberAccessible()) {
                        constructor.setAccessible(true);
                        return constructor.newInstance();
                    } else {
                        throw e;
                    }
                }
            }
            // 根据输入参数类型查找对应的构造器
            constructor = type.getDeclaredConstructor(constructorArgTypes.toArray(new Class[0]));
            try {
                // 采用有参构造函数创建实例
                return constructor.newInstance(constructorArgs.toArray(new Object[0]));
            } catch (IllegalAccessException e) {
                // 如果发生异常,则修改构造函数的访问属性后再次尝试
                if (Reflector.canControlMemberAccessible()) {
                    constructor.setAccessible(true);
                    return constructor.newInstance(constructorArgs.toArray(new Object[0]));
                } else {
                    throw e;
                }
            }
        } catch (Exception e) {
            // 收集所有的参数类型
            String argTypes = Optional.ofNullable(constructorArgTypes).orElseGet(Collections::emptyList)
                .stream().map(Class::getSimpleName).collect(Collectors.joining(","));
            // 收集所有的参数
            String argValues = Optional.ofNullable(constructorArgs).orElseGet(Collections::emptyList)
                .stream().map(String::valueOf).collect(Collectors.joining(","));
            throw new ReflectionException(
                "Error instantiating " + type + " with invalid types (" + argTypes + ") or values (" + argValues
                    + "). Cause: " + e, e);
        }
    }

}
