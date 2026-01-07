package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.TagLength;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TagLengthJsonSerializer extends KmipDataTypeJsonSerializer<TagLength> {

    @Override
    public void serialize(TagLength tagLength, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (tagLength == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!tagLength.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", tagLength.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(tagLength.getKmipTag());
        gen.writeStringField("type", tagLength.getEncodingType().getDescription());
        gen.writeObjectField("value", tagLength.getValue());
        gen.writeEndObject();
    }
}