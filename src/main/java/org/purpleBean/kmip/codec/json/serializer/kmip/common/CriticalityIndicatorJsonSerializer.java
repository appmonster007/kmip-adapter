package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CriticalityIndicator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CriticalityIndicatorJsonSerializer extends KmipDataTypeJsonSerializer<CriticalityIndicator> {

    @Override
    public void serialize(CriticalityIndicator criticalityIndicator, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (criticalityIndicator == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!criticalityIndicator.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", criticalityIndicator.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(criticalityIndicator.getKmipTag());
        gen.writeStringField("type", criticalityIndicator.getEncodingType().getDescription());
        gen.writeObjectField("value", criticalityIndicator.getValue());
        gen.writeEndObject();
    }
}