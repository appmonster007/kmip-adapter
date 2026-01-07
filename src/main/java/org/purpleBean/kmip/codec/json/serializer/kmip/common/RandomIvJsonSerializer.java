package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.RandomIv;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RandomIvJsonSerializer extends KmipDataTypeJsonSerializer<RandomIv> {

    @Override
    public void serialize(RandomIv randomIv, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (randomIv == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!randomIv.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", randomIv.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(randomIv.getKmipTag());
        gen.writeStringField("type", randomIv.getEncodingType().getDescription());
        gen.writeObjectField("value", randomIv.getValue());
        gen.writeEndObject();
    }
}