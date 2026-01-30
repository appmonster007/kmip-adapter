package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateIssuerAlternativeName;

import java.io.IOException;

public class CertificateIssuerAlternativeNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateIssuerAlternativeName, CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder> {

    public CertificateIssuerAlternativeNameJsonDeserializer() {
        super(CertificateIssuerAlternativeName.kmipTag, CertificateIssuerAlternativeName.encodingType);
    }

    @Override
    protected CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder createBuilder() {
        return CertificateIssuerAlternativeName.builder();
    }

    @Override
    protected void setValue(CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected CertificateIssuerAlternativeName build(CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder builder) {
        return builder.build();
    }
}
