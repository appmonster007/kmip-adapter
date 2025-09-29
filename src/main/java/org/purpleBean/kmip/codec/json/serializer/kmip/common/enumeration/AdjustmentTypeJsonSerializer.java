package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.AdjustmentType;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * JSON serializer for AdjustmentType.
 */
public class AdjustmentTypeJsonSerializer extends KmipDataTypeJsonSerializer<AdjustmentType> {

    @Override
    public void serialize(AdjustmentType value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if (value == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("AdjustmentType '%s' is not supported for KMIP spec %s",
                            value.getDescription(), spec)
            );
        }

        if (value.getDescription() == null || value.getDescription().trim().isEmpty()) {
            throw new IllegalStateException("AdjustmentType must have a valid description");
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(value.getKmipTag());
        jsonGenerator.writeStringField("type", value.getEncodingType().getDescription());
        jsonGenerator.writeStringField("value", value.getDescription());
        jsonGenerator.writeEndObject();
    }
}
