package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.PrivateExponent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PrivateExponentJsonSerializer extends KmipDataTypeJsonSerializer<PrivateExponent> {

    @Override
    public void serialize(PrivateExponent privateExponent, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (privateExponent == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!privateExponent.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", privateExponent.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(privateExponent.getKmipTag());
        gen.writeStringField("type", privateExponent.getEncodingType().getDescription());
        gen.writeObjectField("value", privateExponent.getValue());
        gen.writeEndObject();
    }
}