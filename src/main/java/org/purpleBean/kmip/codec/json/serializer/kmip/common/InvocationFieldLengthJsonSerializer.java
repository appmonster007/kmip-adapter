package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.InvocationFieldLength;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class InvocationFieldLengthJsonSerializer extends KmipDataTypeJsonSerializer<InvocationFieldLength> {

    @Override
    public void serialize(InvocationFieldLength invocationFieldLength, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (invocationFieldLength == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!invocationFieldLength.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", invocationFieldLength.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(invocationFieldLength.getKmipTag());
        gen.writeStringField("type", invocationFieldLength.getEncodingType().getDescription());
        gen.writeObjectField("value", invocationFieldLength.getValue());
        gen.writeEndObject();
    }
}