package org.apache.ibatis.reflection.invoker;

import org.apache.ibatis.reflection.Reflector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Clinton Begin
 */
public class MethodInvoker implements Invoker {

    private final Class<?> type;

    private final Method method;

    /**
     * MethodInvoker构造方法
     * @param method 方法
     */
    public MethodInvoker(Method method) {
        this.method = method;

        // 有且只有一个入参时，这里放入入参
        if (method.getParameterTypes().length == 1) {
            type = method.getParameterTypes()[0];
        } else {
            type = method.getReturnType();
        }
    }

    @Override
    public Object invoke(Object target, Object[] args) throws IllegalAccessException, InvocationTargetException {
        try {
            return method.invoke(target, args);
        } catch (IllegalAccessException e) {
            if (Reflector.canControlMemberAccessible()) {
                method.setAccessible(true);
                return method.invoke(target, args);
            } else {
                throw e;
            }
        }
    }

    @Override
    public Class<?> getType() {
        return type;
    }
}
