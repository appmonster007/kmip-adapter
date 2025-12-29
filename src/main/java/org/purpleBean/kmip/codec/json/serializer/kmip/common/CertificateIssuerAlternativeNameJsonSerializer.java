package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CertificateIssuerAlternativeNameJsonSerializer extends KmipDataTypeJsonSerializer<CertificateIssuerAlternativeName> {

    @Override
    public void serialize(CertificateIssuerAlternativeName certificateIssuerAlternativeName, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (certificateIssuerAlternativeName == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!certificateIssuerAlternativeName.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", certificateIssuerAlternativeName.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(certificateIssuerAlternativeName.getKmipTag());
        gen.writeStringField("type", certificateIssuerAlternativeName.getEncodingType().getDescription());
        gen.writeObjectField("value", certificateIssuerAlternativeName.getValue());
        gen.writeEndObject();
    }
}