package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;
import org.purpleBean.kmip.common.structure.CertificateSubject;

import java.io.IOException;

public class CertificateSubjectXmlDeserializer extends AbstractKmipStructureXmlDeserializer<CertificateSubject, CertificateSubject.CertificateSubjectBuilder> {

    public CertificateSubjectXmlDeserializer() {
        super(CertificateSubject.kmipTag);
    }

    @Override
    protected CertificateSubject.CertificateSubjectBuilder createBuilder() {
        return CertificateSubject.builder();
    }

    @Override
    protected void setValue(CertificateSubject.CertificateSubjectBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.CERTIFICATE_SUBJECT_DISTINGUISHED_NAME ->
                    builder.certificateSubjectDistinguishedName(ctxt.readValue(p, CertificateSubjectDistinguishedName.class));
            case KmipTag.Standard.CERTIFICATE_SUBJECT_ALTERNATIVE_NAME ->
                    builder.certificateSubjectAlternativeName(ctxt.readValue(p, CertificateSubjectAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CertificateSubject build(CertificateSubject.CertificateSubjectBuilder builder) {
        return builder.build();
    }
}