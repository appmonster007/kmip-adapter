package org.purpleBean.kmip.codec.ttlv.mapper;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

public abstract class TtlvSerializer<T> {
    public abstract ByteBuffer serialize(T value, TtlvMapper mapper) throws IOException;


    @SuppressWarnings("unchecked")
    public Class<T> handledType() {
        // Prefer generic superclass (if subclasses extend this with a concrete type)
        Type superType = getClass().getGenericSuperclass();
        if (superType instanceof ParameterizedType pt) {
            Type tArg = pt.getActualTypeArguments()[0];
            if (tArg instanceof Class<?> c) {
                return (Class<T>) c;
            }
        }
        // Fallback: try the implemented interfaces to locate TtlvSerializer<T>
        for (Type itf : getClass().getGenericInterfaces()) {
            if (itf instanceof ParameterizedType itfPt) {
                if (itfPt.getRawType() instanceof Class<?> raw && raw.getName().equals(TtlvSerializer.class.getName())) {
                    Type tArg = itfPt.getActualTypeArguments()[0];
                    if (tArg instanceof Class<?> c) {
                        return (Class<T>) c;
                    }
                }
            }
        }
        return null;
    }
}
