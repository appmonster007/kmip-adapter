package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.Fresh;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FreshJsonSerializer extends KmipDataTypeJsonSerializer<Fresh> {

    @Override
    public void serialize(Fresh fresh, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (fresh == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!fresh.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", fresh.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(fresh.getKmipTag());
        gen.writeStringField("type", fresh.getEncodingType().getDescription());
        gen.writeObjectField("value", fresh.getValue());
        gen.writeEndObject();
    }
}