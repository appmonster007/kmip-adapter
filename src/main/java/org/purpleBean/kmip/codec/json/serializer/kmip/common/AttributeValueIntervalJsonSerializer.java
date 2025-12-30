package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AttributeValueIntervalJsonSerializer extends KmipDataTypeJsonSerializer<AttributeValue.Interval> {

    @Override
    public void serialize(AttributeValue.Interval attributeValueInterval, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (attributeValueInterval == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueInterval.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", attributeValueInterval.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(attributeValueInterval.getKmipTag());
        gen.writeStringField("type", attributeValueInterval.getEncodingType().getDescription());
        gen.writeObjectField("value", attributeValueInterval.getValue());
        gen.writeEndObject();
    }
}
