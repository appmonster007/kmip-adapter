package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.X509CertificateSubject;
import org.purpleBean.kmip.model.core.type.SubjectAlternativeName;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;

import java.io.IOException;

public class X509CertificateSubjectJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<X509CertificateSubject, X509CertificateSubject.X509CertificateSubjectBuilder> {

    public X509CertificateSubjectJsonDeserializer() {
        super(X509CertificateSubject.kmipTag, X509CertificateSubject.encodingType);
    }

    @Override
    protected X509CertificateSubject.X509CertificateSubjectBuilder createBuilder() {
        return X509CertificateSubject.builder();
    }

    @Override
    protected void setValue(X509CertificateSubject.X509CertificateSubjectBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
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