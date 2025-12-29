package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.TimeStamp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TimeStampJsonSerializer extends KmipDataTypeJsonSerializer<TimeStamp> {

    @Override
    public void serialize(TimeStamp timeStamp, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (timeStamp == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!timeStamp.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", timeStamp.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(timeStamp.getKmipTag());
        gen.writeStringField("type", timeStamp.getEncodingType().getDescription());
        gen.writeObjectField("value", timeStamp.getValue());
        gen.writeEndObject();
    }
}