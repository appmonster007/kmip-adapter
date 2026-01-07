package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CounterLength;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CounterLengthJsonSerializer extends KmipDataTypeJsonSerializer<CounterLength> {

    @Override
    public void serialize(CounterLength counterLength, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (counterLength == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!counterLength.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", counterLength.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(counterLength.getKmipTag());
        gen.writeStringField("type", counterLength.getEncodingType().getDescription());
        gen.writeObjectField("value", counterLength.getValue());
        gen.writeEndObject();
    }
}