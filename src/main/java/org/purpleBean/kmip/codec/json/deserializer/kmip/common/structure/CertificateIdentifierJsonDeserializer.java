package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.Issuer;
import org.purpleBean.kmip.common.SerialNumber;
import org.purpleBean.kmip.common.structure.CertificateIdentifier;

import java.io.IOException;

public class CertificateIdentifierJsonDeserializer extends AbstractKmipStructureJsonDeserializer<CertificateIdentifier, CertificateIdentifier.CertificateIdentifierBuilder> {

    public CertificateIdentifierJsonDeserializer() {
        super(CertificateIdentifier.kmipTag, CertificateIdentifier.encodingType);
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