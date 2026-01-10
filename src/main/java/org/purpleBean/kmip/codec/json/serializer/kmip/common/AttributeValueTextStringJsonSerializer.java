package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueTextString;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AttributeValueTextStringJsonSerializer extends KmipDataTypeJsonSerializer<AttributeValueTextString> {

    @Override
    public void serialize(AttributeValueTextString attributeValueTextString, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (attributeValueTextString == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueTextString.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", attributeValueTextString.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(attributeValueTextString.getKmipTag());
        gen.writeStringField("type", attributeValueTextString.getEncodingType().getDescription());
        gen.writeObjectField("value", attributeValueTextString.getValue());
        gen.writeEndObject();
    }
}
