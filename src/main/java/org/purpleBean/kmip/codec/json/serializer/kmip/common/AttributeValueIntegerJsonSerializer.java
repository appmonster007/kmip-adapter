package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueInteger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AttributeValueIntegerJsonSerializer extends KmipDataTypeJsonSerializer<AttributeValueInteger> {

    @Override
    public void serialize(AttributeValueInteger attributeValueInteger, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (attributeValueInteger == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueInteger.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", attributeValueInteger.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(attributeValueInteger.getKmipTag());
        gen.writeStringField("type", attributeValueInteger.getEncodingType().getDescription());
        gen.writeObjectField("value", attributeValueInteger.getValue());
        gen.writeEndObject();
    }
}
