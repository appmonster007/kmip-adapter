package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.MediaIdentifier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MediaIdentifierJsonSerializer extends KmipDataTypeJsonSerializer<MediaIdentifier> {

    @Override
    public void serialize(MediaIdentifier mediaIdentifier, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (mediaIdentifier == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!mediaIdentifier.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", mediaIdentifier.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(mediaIdentifier.getKmipTag());
        gen.writeStringField("type", mediaIdentifier.getEncodingType().getDescription());
        gen.writeObjectField("value", mediaIdentifier.getValue());
        gen.writeEndObject();
    }
}