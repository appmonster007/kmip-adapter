package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

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
import org.purpleBean.kmip.common.enumeration.CertificateType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for CertificateType.
 */
public class CertificateTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateType> {
    private final KmipTag kmipTag = CertificateType.kmipTag;
    private final EncodingType encodingType = CertificateType.encodingType;

    @Override
    public CertificateType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CertificateType.class, "Expected XML element object for CertificateType");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CertificateType.class, "Invalid Tag for CertificateType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CertificateType.class, "Missing or invalid '@type' attribute for CertificateType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CertificateType.class, "Missing or non-text '@value' attribute for CertificateType");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        CertificateType certificatetype = new CertificateType(CertificateType.fromName(description));
        if (!certificatetype.isSupported()) {
            throw new NoSuchElementException(
                String.format("CertificateType '%s' not supported for spec %s", description, spec));
        }

        return certificatetype;
    }
}
