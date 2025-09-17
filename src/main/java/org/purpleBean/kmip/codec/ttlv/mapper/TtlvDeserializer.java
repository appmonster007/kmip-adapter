package org.purpleBean.kmip.codec.ttlv.mapper;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

public abstract class TtlvDeserializer<T> {
    public abstract T deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException;

    @SuppressWarnings("unchecked")
    public Class<T> handledType() {
        // Prefer generic superclass
        Type superType = getClass().getGenericSuperclass();
        if (superType instanceof ParameterizedType pt) {
            Type tArg = pt.getActualTypeArguments()[0];
            if (tArg instanceof Class<?> c) {
                return (Class<T>) c;
            }
        }
        // Fallback: inspect implemented interfaces for TtlvDeserializer<T>
        for (Type itf : getClass().getGenericInterfaces()) {
            if (itf instanceof ParameterizedType itfPt) {
                if (itfPt.getRawType() instanceof Class<?> raw && raw.getName().equals(TtlvDeserializer.class.getName())) {
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
