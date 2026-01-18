package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CertificateSubject;
import org.purpleBean.kmip.model.core.type.CertificateSubjectAlternativeName;
import org.purpleBean.kmip.model.core.type.CertificateSubjectDistinguishedName;

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