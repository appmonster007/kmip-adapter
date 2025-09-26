package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import com.fasterxml.jackson.databind.JsonDeserializer;
import org.purpleBean.kmip.KmipDataType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class KmipDataTypeXmlDeserializer<T extends KmipDataType> extends JsonDeserializer<T> {
    @Override
    public Class<?> handledType() {
        // Try to infer the generic parameter (T) from the concrete subclass declaration
        // Handles both raw classes and parameterized types (template classes)
        Type superType = getClass().getGenericSuperclass();
        if (superType instanceof ParameterizedType pt) {
            Type tArg = pt.getActualTypeArguments()[0];
            if (tArg instanceof Class<?> c) {
                return c;
            }
            if (tArg instanceof ParameterizedType parameterized) {
                Type raw = parameterized.getRawType();
                if (raw instanceof Class<?> rc) {
                    return rc;
                }
            }
        }
        return super.handledType();
    }
}
