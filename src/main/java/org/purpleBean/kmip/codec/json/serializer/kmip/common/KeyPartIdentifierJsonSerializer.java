package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.KeyPartIdentifier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class KeyPartIdentifierJsonSerializer extends KmipDataTypeJsonSerializer<KeyPartIdentifier> {

    @Override
    public void serialize(KeyPartIdentifier keyPartIdentifier, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (keyPartIdentifier == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!keyPartIdentifier.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", keyPartIdentifier.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(keyPartIdentifier.getKmipTag());
        gen.writeStringField("type", keyPartIdentifier.getEncodingType().getDescription());
        gen.writeObjectField("value", keyPartIdentifier.getValue());
        gen.writeEndObject();
    }
}