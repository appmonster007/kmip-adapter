package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CertificateSubjectDistinguishedNameJsonSerializer extends KmipDataTypeJsonSerializer<CertificateSubjectDistinguishedName> {

    @Override
    public void serialize(CertificateSubjectDistinguishedName certificateSubjectDistinguishedName, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (certificateSubjectDistinguishedName == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!certificateSubjectDistinguishedName.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", certificateSubjectDistinguishedName.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(certificateSubjectDistinguishedName.getKmipTag());
        gen.writeStringField("type", certificateSubjectDistinguishedName.getEncodingType().getDescription());
        gen.writeObjectField("value", certificateSubjectDistinguishedName.getValue());
        gen.writeEndObject();
    }
}