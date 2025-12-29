package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.IterationCount;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class IterationCountJsonSerializer extends KmipDataTypeJsonSerializer<IterationCount> {

    @Override
    public void serialize(IterationCount iterationCount, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (iterationCount == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!iterationCount.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", iterationCount.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(iterationCount.getKmipTag());
        gen.writeStringField("type", iterationCount.getEncodingType().getDescription());
        gen.writeObjectField("value", iterationCount.getValue());
        gen.writeEndObject();
    }
}