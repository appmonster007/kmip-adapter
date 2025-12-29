package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.RevocationMessage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RevocationMessageJsonSerializer extends KmipDataTypeJsonSerializer<RevocationMessage> {

    @Override
    public void serialize(RevocationMessage revocationMessage, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (revocationMessage == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!revocationMessage.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", revocationMessage.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(revocationMessage.getKmipTag());
        gen.writeStringField("type", revocationMessage.getEncodingType().getDescription());
        gen.writeObjectField("value", revocationMessage.getValue());
        gen.writeEndObject();
    }
}