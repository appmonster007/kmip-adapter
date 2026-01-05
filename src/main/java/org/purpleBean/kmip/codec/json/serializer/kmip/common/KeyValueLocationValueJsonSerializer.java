package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.KeyValueLocationValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class KeyValueLocationValueJsonSerializer extends KmipDataTypeJsonSerializer<KeyValueLocationValue> {

    @Override
    public void serialize(KeyValueLocationValue keyValueLocationValue, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (keyValueLocationValue == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!keyValueLocationValue.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", keyValueLocationValue.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(keyValueLocationValue.getKmipTag());
        gen.writeStringField("type", keyValueLocationValue.getEncodingType().getDescription());
        gen.writeObjectField("value", keyValueLocationValue.getValue());
        gen.writeEndObject();
    }
}