package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateIssuerDistinguishedName;

import java.io.IOException;

public class CertificateIssuerDistinguishedNameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateIssuerDistinguishedName, CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder> {

    public CertificateIssuerDistinguishedNameXmlDeserializer() {
        super(CertificateIssuerDistinguishedName.kmipTag, CertificateIssuerDistinguishedName.encodingType);
    }

    @Override
    protected CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder createBuilder() {
        return CertificateIssuerDistinguishedName.builder();
    }

    @Override
    protected void setValue(CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected CertificateIssuerDistinguishedName build(CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder builder) {
        return builder.build();
    }
}