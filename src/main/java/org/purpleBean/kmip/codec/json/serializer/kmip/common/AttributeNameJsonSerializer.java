package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeName;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * JSON serializer for AttributeName.
 */
public class AttributeNameJsonSerializer extends KmipDataTypeJsonSerializer<AttributeName> {

    @Override
    public void serialize(AttributeName attributeName, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (attributeName == null) return;

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeName.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", attributeName.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(attributeName.getKmipTag());
        gen.writeStringField("type", attributeName.getEncodingType().getDescription());
        gen.writeObjectField("value", attributeName.getValue());
        gen.writeEndObject();
    }
}
