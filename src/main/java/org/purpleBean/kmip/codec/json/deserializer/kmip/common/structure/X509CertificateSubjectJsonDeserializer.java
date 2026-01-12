package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.SubjectAlternativeName;
import org.purpleBean.kmip.common.SubjectDistinguishedName;
import org.purpleBean.kmip.common.structure.X509CertificateSubject;

import java.io.IOException;

public class X509CertificateSubjectJsonDeserializer extends AbstractKmipStructureJsonDeserializer<X509CertificateSubject, X509CertificateSubject.X509CertificateSubjectBuilder> {

    public X509CertificateSubjectJsonDeserializer() {
        super(X509CertificateSubject.kmipTag, X509CertificateSubject.encodingType);
    }

    @Override
    protected X509CertificateSubject.X509CertificateSubjectBuilder createBuilder() {
        return X509CertificateSubject.builder();
    }

    @Override
    protected void setValue(X509CertificateSubject.X509CertificateSubjectBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.SUBJECT_DISTINGUISHED_NAME ->
                    builder.subjectDistinguishedName(ctxt.readValue(p, SubjectDistinguishedName.class));
            case KmipTag.Standard.SUBJECT_ALTERNATIVE_NAME ->
                    builder.subjectAlternativeName(ctxt.readValue(p, SubjectAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected X509CertificateSubject build(X509CertificateSubject.X509CertificateSubjectBuilder builder) {
        return builder.build();
    }
}