package org.purpleBean.kmip.codec.xml.serializer.kmip;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.purpleBean.kmip.KmipDataType;

public abstract class KmipDataTypeXmlSerializer<T extends KmipDataType> extends JsonSerializer<T> {
    @SuppressWarnings("unchecked")
    @Override
    public Class<T> handledType() {
        java.lang.reflect.Type superType = getClass().getGenericSuperclass();
        if (superType instanceof java.lang.reflect.ParameterizedType pt) {
            java.lang.reflect.Type tArg = pt.getActualTypeArguments()[0];
            if (tArg instanceof Class<?> c) {
                return (Class<T>) c;
            }
        }
        return super.handledType();
    }
}
