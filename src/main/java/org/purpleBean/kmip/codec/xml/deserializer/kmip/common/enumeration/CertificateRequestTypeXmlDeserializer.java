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
import org.purpleBean.kmip.common.enumeration.CertificateRequestType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for CertificateRequestType.
 */
public class CertificateRequestTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateRequestType> {
    private final KmipTag kmipTag = CertificateRequestType.kmipTag;
    private final EncodingType encodingType = CertificateRequestType.encodingType;

    @Override
    public CertificateRequestType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CertificateRequestType.class, "Expected XML element object for CertificateRequestType");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CertificateRequestType.class, "Invalid Tag for CertificateRequestType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(CertificateRequestType.class, "Missing or invalid '@type' attribute for CertificateRequestType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CertificateRequestType.class, "Missing or non-text '@value' attribute for CertificateRequestType");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        CertificateRequestType certificaterequesttype = new CertificateRequestType(CertificateRequestType.fromName(description));
        if (!certificaterequesttype.isSupported()) {
            throw new NoSuchElementException(
                String.format("CertificateRequestType '%s' not supported for spec %s", description, spec));
        }

        return certificaterequesttype;
    }
}
