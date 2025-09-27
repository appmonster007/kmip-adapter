package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * JSON serializer for AttributeValue.
 */
public class AttributeValueJsonSerializer extends KmipDataTypeJsonSerializer<AttributeValue> {

    @Override
    public void serialize(AttributeValue attributeValue, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (attributeValue == null) return;

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValue.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", attributeValue.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(attributeValue.getKmipTag());
        gen.writeStringField("type", attributeValue.getEncodingType().getDescription());
        gen.writeObjectField("value", attributeValue.getValue());
        gen.writeEndObject();
    }
}
