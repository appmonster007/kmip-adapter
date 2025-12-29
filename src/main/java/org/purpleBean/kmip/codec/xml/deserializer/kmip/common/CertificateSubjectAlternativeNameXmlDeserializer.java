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
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;

import java.io.IOException;

public class CertificateSubjectAlternativeNameXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateSubjectAlternativeName> {
    private final KmipTag kmipTag = CertificateSubjectAlternativeName.kmipTag;
    private final EncodingType encodingType = CertificateSubjectAlternativeName.encodingType;

    @Override
    public CertificateSubjectAlternativeName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CertificateSubjectAlternativeName.class, "Expected XML object for CertificateSubjectAlternativeName");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CertificateSubjectAlternativeName.class, "Invalid Tag for CertificateSubjectAlternativeName");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CertificateSubjectAlternativeName.class, "Missing or invalid '@type' attribute for CertificateSubjectAlternativeName");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CertificateSubjectAlternativeName.class,
                    "Missing or non-text 'value' for CertificateSubjectAlternativeName");
            return null;
        }

        String value = valueNode.asText();
        CertificateSubjectAlternativeName certificateSubjectAlternativeName = CertificateSubjectAlternativeName.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!certificateSubjectAlternativeName.isSupported()) {
            ctxt.reportInputMismatch(CertificateSubjectAlternativeName.class, "CertificateSubjectAlternativeName not supported for spec " + spec);
            return null;
        }

        return certificateSubjectAlternativeName;
    }
}