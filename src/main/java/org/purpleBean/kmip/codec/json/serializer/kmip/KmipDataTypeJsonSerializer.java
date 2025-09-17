package org.purpleBean.kmip.codec.json.serializer.kmip;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.purpleBean.kmip.KmipDataType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class KmipDataTypeJsonSerializer<T extends KmipDataType> extends JsonSerializer<T> {
    @SuppressWarnings("unchecked")
    @Override
    public Class<T> handledType() {
        // Try to infer the generic parameter (T) from the concrete subclass declaration
        // e.g., class FooSerializer extends KmipDataTypeJsonSerializer<Foo>
        // This allows SimpleModule.addSerializer(JsonSerializer) to work without passing Class explicitly
        Type superType = getClass().getGenericSuperclass();
        if (superType instanceof ParameterizedType pt) {
            Type tArg = pt.getActualTypeArguments()[0];
            if (tArg instanceof Class<?> c) {
                return (Class<T>) c;
            }
        }
        return super.handledType();
    }
}
