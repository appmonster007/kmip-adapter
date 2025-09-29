package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
        gen.writeFieldName("value");
        if (attributeValue.getEncodingType() == EncodingType.STRUCTURE) {
            List<?> fields = attributeValue.getValues();
            gen.writeStartArray();
            for (Object fieldValue : fields) {
                if (fieldValue != null) {
                    gen.writeObject(fieldValue);
                }
            }
            gen.writeEndArray();
        } else {
            gen.writeObject(attributeValue.getValue());
        }
        gen.writeEndObject();
    }
}
