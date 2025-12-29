package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.NetworkIdentifier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class NetworkIdentifierJsonSerializer extends KmipDataTypeJsonSerializer<NetworkIdentifier> {

    @Override
    public void serialize(NetworkIdentifier networkIdentifier, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (networkIdentifier == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!networkIdentifier.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", networkIdentifier.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(networkIdentifier.getKmipTag());
        gen.writeStringField("type", networkIdentifier.getEncodingType().getDescription());
        gen.writeObjectField("value", networkIdentifier.getValue());
        gen.writeEndObject();
    }
}