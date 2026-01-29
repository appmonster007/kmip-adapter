package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;
import org.purpleBean.kmip.model.core.structure.Certificate;
import org.purpleBean.kmip.model.core.type.CertificateValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Certificate, Certificate.CertificateBuilder> {

    public CertificateTtlvDeserializer() {
        super(Certificate.kmipTag, Certificate.encodingType);
    }

    @Override
    protected Certificate.CertificateBuilder createBuilder() {
        return Certificate.builder();
    }

    @Override
    protected void setValue(Certificate.CertificateBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.CERTIFICATE_TYPE ->
                    builder.certificateType(mapper.readValue(p, CertificateType.class));
            case KmipTag.Standard.CERTIFICATE_VALUE ->
                    builder.certificateValue(mapper.readValue(p, CertificateValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Certificate build(Certificate.CertificateBuilder builder) {
        return builder.build();
    }
}