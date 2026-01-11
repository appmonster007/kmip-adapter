package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.SubjectAlternativeName;
import org.purpleBean.kmip.common.SubjectDistinguishedName;
import org.purpleBean.kmip.common.structure.X509CertificateSubject;

import java.io.IOException;

public class X509CertificateSubjectXmlDeserializer extends KmipDataTypeXmlDeserializer<X509CertificateSubject> {
    private final KmipTag kmipTag = X509CertificateSubject.kmipTag;

    @Override
    public X509CertificateSubject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(X509CertificateSubject.class, "Invalid Tag for X509CertificateSubject");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        X509CertificateSubject.X509CertificateSubjectBuilder builder = X509CertificateSubject.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(X509CertificateSubject.class, "Unexpected token: " + p.currentToken());
            }
        }

        X509CertificateSubject x509certificatesubject = builder.build();

        if (!x509certificatesubject.isSupported()) {
            ctxt.reportInputMismatch(X509CertificateSubject.class, "X509CertificateSubject not supported for spec " + spec);
            return null;
        }

        return x509certificatesubject;
    }

    private void setValue(
            X509CertificateSubject.X509CertificateSubjectBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.SUBJECT_DISTINGUISHED_NAME ->
                    builder.subjectDistinguishedName(ctxt.readValue(p, SubjectDistinguishedName.class));
            case KmipTag.Standard.SUBJECT_ALTERNATIVE_NAME ->
                    builder.subjectAlternativeName(ctxt.readValue(p, SubjectAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}