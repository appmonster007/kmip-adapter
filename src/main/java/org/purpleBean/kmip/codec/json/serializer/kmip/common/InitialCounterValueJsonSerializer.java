package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.InitialCounterValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class InitialCounterValueJsonSerializer extends KmipDataTypeJsonSerializer<InitialCounterValue> {

    @Override
    public void serialize(InitialCounterValue initialCounterValue, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (initialCounterValue == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!initialCounterValue.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", initialCounterValue.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(initialCounterValue.getKmipTag());
        gen.writeStringField("type", initialCounterValue.getEncodingType().getDescription());
        gen.writeObjectField("value", initialCounterValue.getValue());
        gen.writeEndObject();
    }
}