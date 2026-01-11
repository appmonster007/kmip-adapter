package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.Issuer;
import org.purpleBean.kmip.common.SerialNumber;
import org.purpleBean.kmip.common.structure.CertificateIdentifier;

import java.io.IOException;

public class CertificateIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateIdentifier> {
    private final KmipTag kmipTag = CertificateIdentifier.kmipTag;

    @Override
    public CertificateIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(CertificateIdentifier.class, "Invalid Tag for CertificateIdentifier");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        CertificateIdentifier.CertificateIdentifierBuilder builder = CertificateIdentifier.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(CertificateIdentifier.class, "Unexpected token: " + p.currentToken());
            }
        }

        CertificateIdentifier certificateidentifier = builder.build();

        if (!certificateidentifier.isSupported()) {
            ctxt.reportInputMismatch(CertificateIdentifier.class, "CertificateIdentifier not supported for spec " + spec);
            return null;
        }

        return certificateidentifier;
    }

    private void setValue(
            CertificateIdentifier.CertificateIdentifierBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER -> builder.issuer(ctxt.readValue(p, Issuer.class));
            case KmipTag.Standard.SERIAL_NUMBER -> builder.serialNumber(ctxt.readValue(p, SerialNumber.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}