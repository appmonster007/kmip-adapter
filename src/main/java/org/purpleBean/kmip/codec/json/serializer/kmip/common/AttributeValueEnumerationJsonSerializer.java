package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AttributeValueEnumerationJsonSerializer extends KmipDataTypeJsonSerializer<AttributeValue.Enumeration> {

    @Override
    public void serialize(AttributeValue.Enumeration attributeValueEnumeration, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (attributeValueEnumeration == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueEnumeration.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", attributeValueEnumeration.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(attributeValueEnumeration.getKmipTag());
        gen.writeStringField("type", attributeValueEnumeration.getEncodingType().getDescription());
        gen.writeObjectField("value", attributeValueEnumeration.getValue());
        gen.writeEndObject();
    }
}
