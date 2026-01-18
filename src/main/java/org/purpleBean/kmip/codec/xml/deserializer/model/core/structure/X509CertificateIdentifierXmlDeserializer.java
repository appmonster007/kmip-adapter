package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.X509CertificateIdentifier;
import org.purpleBean.kmip.model.core.type.CertificateSerialNumber;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;

import java.io.IOException;

public class X509CertificateIdentifierXmlDeserializer extends AbstractKmipStructureXmlDeserializer<X509CertificateIdentifier, X509CertificateIdentifier.X509CertificateIdentifierBuilder> {

    public X509CertificateIdentifierXmlDeserializer() {
        super(X509CertificateIdentifier.kmipTag);
    }

    @Override
    protected X509CertificateIdentifier.X509CertificateIdentifierBuilder createBuilder() {
        return X509CertificateIdentifier.builder();
    }

    @Override
    protected void setValue(X509CertificateIdentifier.X509CertificateIdentifierBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME ->
                    builder.issuerDistinguishedName(ctxt.readValue(p, IssuerDistinguishedName.class));
            case KmipTag.Standard.CERTIFICATE_SERIAL_NUMBER ->
                    builder.certificateSerialNumber(ctxt.readValue(p, CertificateSerialNumber.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected X509CertificateIdentifier build(X509CertificateIdentifier.X509CertificateIdentifierBuilder builder) {
        return builder.build();
    }
}