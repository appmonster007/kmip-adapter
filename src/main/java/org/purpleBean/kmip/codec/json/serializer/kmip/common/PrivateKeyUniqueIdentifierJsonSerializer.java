package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.PrivateKeyUniqueIdentifier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PrivateKeyUniqueIdentifierJsonSerializer extends KmipDataTypeJsonSerializer<PrivateKeyUniqueIdentifier> {

    @Override
    public void serialize(PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (privateKeyUniqueIdentifier == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!privateKeyUniqueIdentifier.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", privateKeyUniqueIdentifier.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(privateKeyUniqueIdentifier.getKmipTag());
        gen.writeStringField("type", privateKeyUniqueIdentifier.getEncodingType().getDescription());
        gen.writeObjectField("value", privateKeyUniqueIdentifier.getValue());
        gen.writeEndObject();
    }
}