package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.Username;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class UsernameJsonSerializer extends KmipDataTypeJsonSerializer<Username> {

    @Override
    public void serialize(Username username, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (username == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!username.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", username.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(username.getKmipTag());
        gen.writeStringField("type", username.getEncodingType().getDescription());
        gen.writeObjectField("value", username.getValue());
        gen.writeEndObject();
    }
}