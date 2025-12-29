package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.PublicExponent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PublicExponentJsonSerializer extends KmipDataTypeJsonSerializer<PublicExponent> {

    @Override
    public void serialize(PublicExponent publicExponent, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (publicExponent == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!publicExponent.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", publicExponent.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(publicExponent.getKmipTag());
        gen.writeStringField("type", publicExponent.getEncodingType().getDescription());
        gen.writeObjectField("value", publicExponent.getValue());
        gen.writeEndObject();
    }
}