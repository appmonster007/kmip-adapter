package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ReplacedUniqueIdentifier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ReplacedUniqueIdentifierJsonSerializer extends KmipDataTypeJsonSerializer<ReplacedUniqueIdentifier> {

    @Override
    public void serialize(ReplacedUniqueIdentifier replacedUniqueIdentifier, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (replacedUniqueIdentifier == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!replacedUniqueIdentifier.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", replacedUniqueIdentifier.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(replacedUniqueIdentifier.getKmipTag());
        gen.writeStringField("type", replacedUniqueIdentifier.getEncodingType().getDescription());
        gen.writeObjectField("value", replacedUniqueIdentifier.getValue());
        gen.writeEndObject();
    }
}