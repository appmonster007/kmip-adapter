package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.Offset;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class OffsetJsonSerializer extends KmipDataTypeJsonSerializer<Offset> {

    @Override
    public void serialize(Offset offset, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (offset == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!offset.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", offset.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(offset.getKmipTag());
        gen.writeStringField("type", offset.getEncodingType().getDescription());
        gen.writeObjectField("value", offset.getValue());
        gen.writeEndObject();
    }
}