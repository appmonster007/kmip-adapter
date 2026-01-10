package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueLongInteger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AttributeValueLongIntegerJsonSerializer extends KmipDataTypeJsonSerializer<AttributeValueLongInteger> {

    @Override
    public void serialize(AttributeValueLongInteger attributeValueLongInteger, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (attributeValueLongInteger == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueLongInteger.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", attributeValueLongInteger.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(attributeValueLongInteger.getKmipTag());
        gen.writeStringField("type", attributeValueLongInteger.getEncodingType().getDescription());
        gen.writeObjectField("value", attributeValueLongInteger.getValue());
        gen.writeEndObject();
    }
}
