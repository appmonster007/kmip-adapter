package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;
import java.util.List;

public class CertificateIssuerDistinguishedNameJsonSerializer extends KmipDataTypeJsonSerializer<CertificateIssuerDistinguishedName> {

    @Override
    public void serialize(CertificateIssuerDistinguishedName certificateIssuerDistinguishedName, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (certificateIssuerDistinguishedName == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!certificateIssuerDistinguishedName.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", certificateIssuerDistinguishedName.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(certificateIssuerDistinguishedName.getKmipTag());
        gen.writeStringField("type", certificateIssuerDistinguishedName.getEncodingType().getDescription());
        gen.writeObjectField("value", certificateIssuerDistinguishedName.getValue());
        gen.writeEndObject();
    }
}