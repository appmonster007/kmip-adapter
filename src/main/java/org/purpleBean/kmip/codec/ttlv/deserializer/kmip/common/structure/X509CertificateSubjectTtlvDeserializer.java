package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.SubjectAlternativeName;
import org.purpleBean.kmip.common.SubjectDistinguishedName;
import org.purpleBean.kmip.common.structure.X509CertificateSubject;

import java.io.IOException;
import java.nio.ByteBuffer;

public class X509CertificateSubjectTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<X509CertificateSubject, X509CertificateSubject.X509CertificateSubjectBuilder> {

    public X509CertificateSubjectTtlvDeserializer() {
        super(X509CertificateSubject.kmipTag);
    }

    @Override
    protected X509CertificateSubject.X509CertificateSubjectBuilder createBuilder() {
        return X509CertificateSubject.builder();
    }

    @Override
    protected void setValue(X509CertificateSubject.X509CertificateSubjectBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.SUBJECT_DISTINGUISHED_NAME ->
                    builder.subjectDistinguishedName(mapper.readValue(p, SubjectDistinguishedName.class));
            case KmipTag.Standard.SUBJECT_ALTERNATIVE_NAME ->
                    builder.subjectAlternativeName(mapper.readValue(p, SubjectAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected X509CertificateSubject build(X509CertificateSubject.X509CertificateSubjectBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return X509CertificateSubject.encodingType;
    }
}