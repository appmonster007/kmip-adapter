package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CertificateValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CertificateValueJsonSerializer extends KmipDataTypeJsonSerializer<CertificateValue> {

    @Override
    public void serialize(CertificateValue certificateValue, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (certificateValue == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!certificateValue.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", certificateValue.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(certificateValue.getKmipTag());
        gen.writeStringField("type", certificateValue.getEncodingType().getDescription());
        gen.writeObjectField("value", certificateValue.getValue());
        gen.writeEndObject();
    }
}