package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.IssuerDistinguishedName;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class IssuerDistinguishedNameJsonSerializer extends KmipDataTypeJsonSerializer<IssuerDistinguishedName> {

    @Override
    public void serialize(IssuerDistinguishedName issuerDistinguishedName, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (issuerDistinguishedName == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!issuerDistinguishedName.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", issuerDistinguishedName.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(issuerDistinguishedName.getKmipTag());
        gen.writeStringField("type", issuerDistinguishedName.getEncodingType().getDescription());
        gen.writeObjectField("value", issuerDistinguishedName.getValue());
        gen.writeEndObject();
    }
}
