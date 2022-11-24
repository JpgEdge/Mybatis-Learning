package org.apache.ibatis.reflection;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

/**
 * @author Clinton Begin
 */
public final class SystemMetaObject {

    public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();

    public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    private SystemMetaObject() {
        // Prevent Instantiation of Static Class
    }

    public static MetaObject forObject(Object object) {
        return MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,
            new DefaultReflectorFactory());
    }    public static final MetaObject NULL_META_OBJECT = MetaObject.forObject(new NullObject(), DEFAULT_OBJECT_FACTORY,
        DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());

    private static class NullObject {
    }



}
