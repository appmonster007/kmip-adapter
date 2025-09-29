package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.AttestationType;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * JSON serializer for AttestationType.
 */
public class AttestationTypeJsonSerializer extends KmipDataTypeJsonSerializer<AttestationType> {

    @Override
    public void serialize(AttestationType value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if (value == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("AttestationType '%s' is not supported for KMIP spec %s",
                            value.getDescription(), spec)
            );
        }

        if (value.getDescription() == null || value.getDescription().trim().isEmpty()) {
            throw new IllegalStateException("AttestationType must have a valid description");
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(value.getKmipTag());
        jsonGenerator.writeStringField("type", value.getEncodingType().getDescription());
        jsonGenerator.writeStringField("value", value.getDescription());
        jsonGenerator.writeEndObject();
    }
}
