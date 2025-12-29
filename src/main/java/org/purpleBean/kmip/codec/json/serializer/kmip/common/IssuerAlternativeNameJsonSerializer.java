package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.IssuerAlternativeName;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class IssuerAlternativeNameJsonSerializer extends KmipDataTypeJsonSerializer<IssuerAlternativeName> {

    @Override
    public void serialize(IssuerAlternativeName issuerAlternativeName, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (issuerAlternativeName == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!issuerAlternativeName.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", issuerAlternativeName.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(issuerAlternativeName.getKmipTag());
        gen.writeStringField("type", issuerAlternativeName.getEncodingType().getDescription());
        gen.writeObjectField("value", issuerAlternativeName.getValue());
        gen.writeEndObject();
    }
}