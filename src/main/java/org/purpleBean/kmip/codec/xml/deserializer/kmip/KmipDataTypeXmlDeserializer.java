package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import com.fasterxml.jackson.databind.JsonDeserializer;
import org.purpleBean.kmip.KmipDataType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class KmipDataTypeXmlDeserializer<T extends KmipDataType> extends JsonDeserializer<T> {
    @Override
    public Class<?> handledType() {
        // Infer the generic parameter (T) from the concrete subclass declaration
        Type superType = getClass().getGenericSuperclass();
        if (superType instanceof ParameterizedType pt) {
            Type tArg = pt.getActualTypeArguments()[0];
            if (tArg instanceof Class<?> c) {
                return c;
            }
        }
        return super.handledType();
    }
}
