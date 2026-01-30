package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;

import java.io.IOException;

public class CertificateTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateType, CertificateType.CertificateTypeBuilder> {

    public CertificateTypeJsonDeserializer() {
        super(CertificateType.kmipTag, CertificateType.encodingType);
    }

    @Override
    protected CertificateType.CertificateTypeBuilder createBuilder() {
        return CertificateType.builder();
    }

    @Override
    protected void setValue(CertificateType.CertificateTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(CertificateType.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected CertificateType build(CertificateType.CertificateTypeBuilder builder) {
        return builder.build();
    }
}
