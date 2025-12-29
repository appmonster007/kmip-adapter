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
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;

import java.io.IOException;

public class CertificateSubjectDistinguishedNameXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateSubjectDistinguishedName> {
    private final KmipTag kmipTag = CertificateSubjectDistinguishedName.kmipTag;
    private final EncodingType encodingType = CertificateSubjectDistinguishedName.encodingType;

    @Override
    public CertificateSubjectDistinguishedName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CertificateSubjectDistinguishedName.class, "Expected XML object for CertificateSubjectDistinguishedName");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CertificateSubjectDistinguishedName.class, "Invalid Tag for CertificateSubjectDistinguishedName");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CertificateSubjectDistinguishedName.class, "Missing or invalid '@type' attribute for CertificateSubjectDistinguishedName");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CertificateSubjectDistinguishedName.class,
                    "Missing or non-text 'value' for CertificateSubjectDistinguishedName");
            return null;
        }

        String value = valueNode.asText();
        CertificateSubjectDistinguishedName certificateSubjectDistinguishedName = CertificateSubjectDistinguishedName.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!certificateSubjectDistinguishedName.isSupported()) {
            ctxt.reportInputMismatch(CertificateSubjectDistinguishedName.class, "CertificateSubjectDistinguishedName not supported for spec " + spec);
            return null;
        }

        return certificateSubjectDistinguishedName;
    }
}