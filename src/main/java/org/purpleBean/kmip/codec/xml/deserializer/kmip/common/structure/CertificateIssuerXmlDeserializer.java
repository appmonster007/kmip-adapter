package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;
import org.purpleBean.kmip.common.structure.CertificateIssuer;

import java.io.IOException;

public class CertificateIssuerXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateIssuer> {
    private final KmipTag kmipTag = CertificateIssuer.kmipTag;

    @Override
    public CertificateIssuer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(CertificateIssuer.class, "Invalid Tag for CertificateIssuer");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        CertificateIssuer.CertificateIssuerBuilder builder = CertificateIssuer.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(CertificateIssuer.class, "Unexpected token: " + p.currentToken());
            }
        }

        CertificateIssuer certificateissuer = builder.build();

        if (!certificateissuer.isSupported()) {
            ctxt.reportInputMismatch(CertificateIssuer.class, "CertificateIssuer not supported for spec " + spec);
            return null;
        }

        return certificateissuer;
    }

    private void setValue(
            CertificateIssuer.CertificateIssuerBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.CERTIFICATE_ISSUER_DISTINGUISHED_NAME ->
                    builder.certificateIssuerDistinguishedName(ctxt.readValue(p, CertificateIssuerDistinguishedName.class));
            case KmipTag.Standard.CERTIFICATE_ISSUER_ALTERNATIVE_NAME ->
                    builder.certificateIssuerAlternativeName(ctxt.readValue(p, CertificateIssuerAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}