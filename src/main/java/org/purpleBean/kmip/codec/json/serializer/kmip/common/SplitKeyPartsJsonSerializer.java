package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.SplitKeyParts;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SplitKeyPartsJsonSerializer extends KmipDataTypeJsonSerializer<SplitKeyParts> {

    @Override
    public void serialize(SplitKeyParts splitKeyParts, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (splitKeyParts == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!splitKeyParts.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", splitKeyParts.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(splitKeyParts.getKmipTag());
        gen.writeStringField("type", splitKeyParts.getEncodingType().getDescription());
        gen.writeObjectField("value", splitKeyParts.getValue());
        gen.writeEndObject();
    }
}