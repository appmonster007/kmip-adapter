package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CertificateSerialNumber;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class CertificateSerialNumberJsonSerializer extends KmipDataTypeJsonSerializer<CertificateSerialNumber> {

    @Override
    public void serialize(CertificateSerialNumber certificateSerialNumber, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (certificateSerialNumber == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!certificateSerialNumber.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", certificateSerialNumber.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(certificateSerialNumber.getKmipTag());
        gen.writeStringField("type", certificateSerialNumber.getEncodingType().getDescription());
        gen.writeObjectField("value", certificateSerialNumber.getValue());
        gen.writeEndObject();
    }
}
