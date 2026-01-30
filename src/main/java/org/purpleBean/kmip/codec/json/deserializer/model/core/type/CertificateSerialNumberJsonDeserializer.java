package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateSerialNumber;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateSerialNumberJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateSerialNumber, CertificateSerialNumber.CertificateSerialNumberBuilder> {

    public CertificateSerialNumberJsonDeserializer() {
        super(CertificateSerialNumber.kmipTag, CertificateSerialNumber.encodingType);
    }

    @Override
    protected CertificateSerialNumber.CertificateSerialNumberBuilder createBuilder() {
        return CertificateSerialNumber.builder();
    }

    @Override
    protected void setValue(CertificateSerialNumber.CertificateSerialNumberBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected CertificateSerialNumber build(CertificateSerialNumber.CertificateSerialNumberBuilder builder) {
        return builder.build();
    }
}
