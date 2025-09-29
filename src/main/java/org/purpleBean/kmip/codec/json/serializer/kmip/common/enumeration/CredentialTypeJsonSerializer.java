package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.CredentialType;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * JSON serializer for CredentialType.
 */
public class CredentialTypeJsonSerializer extends KmipDataTypeJsonSerializer<CredentialType> {

    @Override
    public void serialize(CredentialType value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if (value == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("CredentialType '%s' is not supported for KMIP spec %s",
                            value.getDescription(), spec)
            );
        }

        if (value.getDescription() == null || value.getDescription().trim().isEmpty()) {
            throw new IllegalStateException("CredentialType must have a valid description");
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(value.getKmipTag());
        jsonGenerator.writeStringField("type", value.getEncodingType().getDescription());
        jsonGenerator.writeStringField("value", value.getDescription());
        jsonGenerator.writeEndObject();
    }
}
