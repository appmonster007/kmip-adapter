package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeIndex;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * JSON serializer for AttributeIndex.
 */
public class AttributeIndexJsonSerializer extends KmipDataTypeJsonSerializer<AttributeIndex> {

    @Override
    public void serialize(AttributeIndex attributeIndex, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (attributeIndex == null) return;

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeIndex.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", attributeIndex.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(attributeIndex.getKmipTag());
        gen.writeStringField("type", attributeIndex.getEncodingType().getDescription());
        gen.writeNumberField("value", attributeIndex.getValue());
        gen.writeEndObject();
    }
}
