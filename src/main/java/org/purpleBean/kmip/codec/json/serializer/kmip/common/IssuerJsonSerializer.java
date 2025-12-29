package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.Issuer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class IssuerJsonSerializer extends KmipDataTypeJsonSerializer<Issuer> {

    @Override
    public void serialize(Issuer issuer, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (issuer == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!issuer.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", issuer.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(issuer.getKmipTag());
        gen.writeStringField("type", issuer.getEncodingType().getDescription());
        gen.writeObjectField("value", issuer.getValue());
        gen.writeEndObject();
    }
}