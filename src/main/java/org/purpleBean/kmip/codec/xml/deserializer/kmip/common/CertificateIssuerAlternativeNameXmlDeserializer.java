package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;

import java.io.IOException;

public class CertificateIssuerAlternativeNameXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateIssuerAlternativeName> {
    private final KmipTag kmipTag = CertificateIssuerAlternativeName.kmipTag;
    private final EncodingType encodingType = CertificateIssuerAlternativeName.encodingType;

    @Override
    public CertificateIssuerAlternativeName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CertificateIssuerAlternativeName.class, "Expected XML object for CertificateIssuerAlternativeName");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CertificateIssuerAlternativeName.class, "Invalid Tag for CertificateIssuerAlternativeName");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CertificateIssuerAlternativeName.class, "Missing or invalid '@type' attribute for CertificateIssuerAlternativeName");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CertificateIssuerAlternativeName.class,
                    "Missing or non-text 'value' for CertificateIssuerAlternativeName");
            return null;
        }

        String value = valueNode.asText();
        CertificateIssuerAlternativeName certificateIssuerAlternativeName = CertificateIssuerAlternativeName.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!certificateIssuerAlternativeName.isSupported()) {
            ctxt.reportInputMismatch(CertificateIssuerAlternativeName.class, "CertificateIssuerAlternativeName not supported for spec " + spec);
            return null;
        }

        return certificateIssuerAlternativeName;
    }
}