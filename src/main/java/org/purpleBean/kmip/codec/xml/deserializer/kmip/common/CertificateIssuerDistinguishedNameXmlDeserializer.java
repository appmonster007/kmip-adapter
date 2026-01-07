package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;

import java.io.IOException;

public class CertificateIssuerDistinguishedNameXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateIssuerDistinguishedName> {
    private final KmipTag kmipTag = CertificateIssuerDistinguishedName.kmipTag;
    private final EncodingType encodingType = CertificateIssuerDistinguishedName.encodingType;

    @Override
    public CertificateIssuerDistinguishedName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CertificateIssuerDistinguishedName.class, "Expected XML object for CertificateIssuerDistinguishedName");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
              && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CertificateIssuerDistinguishedName.class, "Invalid Tag for CertificateIssuerDistinguishedName");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CertificateIssuerDistinguishedName.class, "Missing or invalid '@type' attribute for CertificateIssuerDistinguishedName");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CertificateIssuerDistinguishedName.class,
                "Missing or non-text 'value' for CertificateIssuerDistinguishedName");
            return null;
        }

        String value = valueNode.asText();
        CertificateIssuerDistinguishedName certificateIssuerDistinguishedName = CertificateIssuerDistinguishedName.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!certificateIssuerDistinguishedName.isSupported()) {
            ctxt.reportInputMismatch(CertificateIssuerDistinguishedName.class, "CertificateIssuerDistinguishedName not supported for spec " + spec);
            return null;
        }

        return certificateIssuerDistinguishedName;
    }
}