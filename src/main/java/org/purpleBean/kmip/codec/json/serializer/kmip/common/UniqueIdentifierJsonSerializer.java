package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.UniqueIdentifier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class UniqueIdentifierJsonSerializer extends KmipDataTypeJsonSerializer<UniqueIdentifier> {

    @Override
    public void serialize(UniqueIdentifier uniqueIdentifier, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (uniqueIdentifier == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!uniqueIdentifier.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s is not supported for KMIP spec %s", uniqueIdentifier.getKmipTag().getDescription(), spec));
        }

        gen.writeStartObject();
        gen.writeObject(uniqueIdentifier.getKmipTag());
        gen.writeStringField("type", uniqueIdentifier.getEncodingType().getDescription());
        gen.writeObjectField("value", uniqueIdentifier.getValue());
        gen.writeEndObject();
    }
}
