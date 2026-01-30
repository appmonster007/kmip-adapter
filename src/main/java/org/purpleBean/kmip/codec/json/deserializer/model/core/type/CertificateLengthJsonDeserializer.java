package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateLength;

import java.io.IOException;

public class CertificateLengthJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateLength, CertificateLength.CertificateLengthBuilder> {

    public CertificateLengthJsonDeserializer() {
        super(CertificateLength.kmipTag, CertificateLength.encodingType);
    }

    @Override
    protected CertificateLength.CertificateLengthBuilder createBuilder() {
        return CertificateLength.builder();
    }

    @Override
    protected void setValue(CertificateLength.CertificateLengthBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected CertificateLength build(CertificateLength.CertificateLengthBuilder builder) {
        return builder.build();
    }
}
