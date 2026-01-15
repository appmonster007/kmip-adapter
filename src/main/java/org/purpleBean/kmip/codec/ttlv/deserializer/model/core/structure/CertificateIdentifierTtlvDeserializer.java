package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.Issuer;
import org.purpleBean.kmip.model.core.type.SerialNumber;
import org.purpleBean.kmip.model.core.structure.CertificateIdentifier;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateIdentifierTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<CertificateIdentifier, CertificateIdentifier.CertificateIdentifierBuilder> {

    public CertificateIdentifierTtlvDeserializer() {
        super(CertificateIdentifier.kmipTag);
    }

    @Override
    protected CertificateIdentifier.CertificateIdentifierBuilder createBuilder() {
        return CertificateIdentifier.builder();
    }

    @Override
    protected void setValue(CertificateIdentifier.CertificateIdentifierBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER -> builder.issuer(mapper.readValue(p, Issuer.class));
            case KmipTag.Standard.SERIAL_NUMBER -> builder.serialNumber(mapper.readValue(p, SerialNumber.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CertificateIdentifier build(CertificateIdentifier.CertificateIdentifierBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return CertificateIdentifier.encodingType;
    }
}