package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.SplitKeyThreshold;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SplitKeyThresholdJsonSerializer extends KmipDataTypeJsonSerializer<SplitKeyThreshold> {

    @Override
    public void serialize(SplitKeyThreshold splitKeyThreshold, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (splitKeyThreshold == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!splitKeyThreshold.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", splitKeyThreshold.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(splitKeyThreshold.getKmipTag());
        gen.writeStringField("type", splitKeyThreshold.getEncodingType().getDescription());
        gen.writeObjectField("value", splitKeyThreshold.getValue());
        gen.writeEndObject();
    }
}