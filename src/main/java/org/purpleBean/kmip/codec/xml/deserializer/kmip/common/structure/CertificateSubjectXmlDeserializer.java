package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;
import org.purpleBean.kmip.common.structure.CertificateSubject;

import java.io.IOException;

public class CertificateSubjectXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateSubject> {
    private final KmipTag kmipTag = CertificateSubject.kmipTag;

    @Override
    public CertificateSubject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(CertificateSubject.class, "Invalid Tag for CertificateSubject");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        CertificateSubject.CertificateSubjectBuilder builder = CertificateSubject.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(CertificateSubject.class, "Unexpected token: " + p.currentToken());
            }
        }

        CertificateSubject certificatesubject = builder.build();

        if (!certificatesubject.isSupported()) {
            ctxt.reportInputMismatch(CertificateSubject.class, "CertificateSubject not supported for spec " + spec);
            return null;
        }

        return certificatesubject;
    }

    private void setValue(
            CertificateSubject.CertificateSubjectBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.CERTIFICATE_SUBJECT_DISTINGUISHED_NAME ->
                    builder.certificateSubjectDistinguishedName(ctxt.readValue(p, CertificateSubjectDistinguishedName.class));
            case KmipTag.Standard.CERTIFICATE_SUBJECT_ALTERNATIVE_NAME ->
                    builder.certificateSubjectAlternativeName(ctxt.readValue(p, CertificateSubjectAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}