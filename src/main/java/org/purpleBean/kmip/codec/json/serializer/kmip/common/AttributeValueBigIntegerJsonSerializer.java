package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AttributeValueBigIntegerJsonSerializer extends KmipDataTypeJsonSerializer<AttributeValue.BigInteger> {

    @Override
    public void serialize(AttributeValue.BigInteger attributeValueBigInteger, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (attributeValueBigInteger == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueBigInteger.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", attributeValueBigInteger.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(attributeValueBigInteger.getKmipTag());
        gen.writeStringField("type", attributeValueBigInteger.getEncodingType().getDescription());
        gen.writeObjectField("value", attributeValueBigInteger.getValue());
        gen.writeEndObject();
    }
}
