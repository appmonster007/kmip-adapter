package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CertificateSubjectAlternativeNameJsonSerializer extends KmipDataTypeJsonSerializer<CertificateSubjectAlternativeName> {

    @Override
    public void serialize(CertificateSubjectAlternativeName certificateSubjectAlternativeName, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (certificateSubjectAlternativeName == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!certificateSubjectAlternativeName.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", certificateSubjectAlternativeName.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(certificateSubjectAlternativeName.getKmipTag());
        gen.writeStringField("type", certificateSubjectAlternativeName.getEncodingType().getDescription());
        gen.writeObjectField("value", certificateSubjectAlternativeName.getValue());
        gen.writeEndObject();
    }
}