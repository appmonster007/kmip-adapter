package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.CertificateIdentifier;
import org.purpleBean.kmip.model.core.type.Issuer;
import org.purpleBean.kmip.model.core.type.SerialNumber;

import java.io.IOException;

public class CertificateIdentifierXmlDeserializer extends AbstractKmipStructureXmlDeserializer<CertificateIdentifier, CertificateIdentifier.CertificateIdentifierBuilder> {

    public CertificateIdentifierXmlDeserializer() {
        super(CertificateIdentifier.kmipTag);
    }

    @Override
    protected CertificateIdentifier.CertificateIdentifierBuilder createBuilder() {
        return CertificateIdentifier.builder();
    }

    @Override
    protected void setValue(CertificateIdentifier.CertificateIdentifierBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER -> builder.issuer(ctxt.readValue(p, Issuer.class));
            case KmipTag.Standard.SERIAL_NUMBER -> builder.serialNumber(ctxt.readValue(p, SerialNumber.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CertificateIdentifier build(CertificateIdentifier.CertificateIdentifierBuilder builder) {
        return builder.build();
    }
}