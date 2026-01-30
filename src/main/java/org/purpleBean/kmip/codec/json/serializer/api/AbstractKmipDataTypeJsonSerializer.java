package org.purpleBean.kmip.codec.json.serializer.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.api.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public abstract class AbstractKmipDataTypeJsonSerializer<T extends KmipDataType> extends KmipDataTypeJsonSerializer<T> {

    @Override
    public void serialize(T obj, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: Null check
        if (obj == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!obj.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", obj.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();

        KmipTag kmipTag = obj.getKmipTag();
        if (kmipTag.isCustom()) {
            gen.writeStringField("tag", kmipTag.getTagHexString());
            gen.writeStringField("name", kmipTag.getDescription());
        } else {
            gen.writeStringField("tag", kmipTag.getDescription());
        }
        gen.writeStringField("type", obj.getEncodingType().getDescription());

        gen.writeFieldName("value");
        var value = obj.getValue();
        if (obj.getEncodingType() == EncodingType.STRUCTURE) {
            KmipDataType[] nestedValues = (KmipDataType[]) value;
            if (nestedValues != null) {
                gen.writeStartArray();
                for (KmipDataType kmipDataType : nestedValues) {
                    if (kmipDataType != null) {
                        serializers.defaultSerializeValue(kmipDataType, gen);
                    }
                }
                gen.writeEndArray();
            }
        } else if (obj.getEncodingType() == EncodingType.ENUMERATION) {
            serializers.defaultSerializeValue(((KmipEnumeration.Value<?>) value).getDescription(), gen);
        } else {
            serializers.defaultSerializeValue(value, gen);
        }

        gen.writeEndObject();
    }
}