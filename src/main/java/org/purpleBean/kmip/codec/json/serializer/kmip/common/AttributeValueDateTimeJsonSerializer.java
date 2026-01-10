package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueDateTime;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AttributeValueDateTimeJsonSerializer extends KmipDataTypeJsonSerializer<AttributeValueDateTime> {

    @Override
    public void serialize(AttributeValueDateTime attributeValueDateTime, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (attributeValueDateTime == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueDateTime.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", attributeValueDateTime.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(attributeValueDateTime.getKmipTag());
        gen.writeStringField("type", attributeValueDateTime.getEncodingType().getDescription());
        gen.writeObjectField("value", attributeValueDateTime.getValue());
        gen.writeEndObject();
    }
}
