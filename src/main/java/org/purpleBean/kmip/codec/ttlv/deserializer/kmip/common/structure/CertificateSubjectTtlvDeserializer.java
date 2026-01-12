package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;
import org.purpleBean.kmip.common.structure.CertificateSubject;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateSubjectTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<CertificateSubject, CertificateSubject.CertificateSubjectBuilder> {

    public CertificateSubjectTtlvDeserializer() {
        super(CertificateSubject.kmipTag);
    }

    @Override
    protected CertificateSubject.CertificateSubjectBuilder createBuilder() {
        return CertificateSubject.builder();
    }

    @Override
    protected void setValue(CertificateSubject.CertificateSubjectBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.CERTIFICATE_SUBJECT_DISTINGUISHED_NAME ->
                    builder.certificateSubjectDistinguishedName(mapper.readValue(p, CertificateSubjectDistinguishedName.class));
            case KmipTag.Standard.CERTIFICATE_SUBJECT_ALTERNATIVE_NAME ->
                    builder.certificateSubjectAlternativeName(mapper.readValue(p, CertificateSubjectAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CertificateSubject build(CertificateSubject.CertificateSubjectBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return CertificateSubject.encodingType;
    }
}