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
import org.purpleBean.kmip.common.enumeration.DigitalSignatureAlgorithm;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for DigitalSignatureAlgorithm.
 */
public class DigitalSignatureAlgorithmXmlDeserializer extends KmipDataTypeXmlDeserializer<DigitalSignatureAlgorithm> {
    private final KmipTag kmipTag = DigitalSignatureAlgorithm.kmipTag;
    private final EncodingType encodingType = DigitalSignatureAlgorithm.encodingType;

    @Override
    public DigitalSignatureAlgorithm deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(DigitalSignatureAlgorithm.class, "Expected XML element object for DigitalSignatureAlgorithm");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(DigitalSignatureAlgorithm.class, "Invalid Tag for DigitalSignatureAlgorithm");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(DigitalSignatureAlgorithm.class, "Missing or invalid '@type' attribute for DigitalSignatureAlgorithm");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DigitalSignatureAlgorithm.class, "Missing or non-text '@value' attribute for DigitalSignatureAlgorithm");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        DigitalSignatureAlgorithm digitalsignaturealgorithm = new DigitalSignatureAlgorithm(DigitalSignatureAlgorithm.fromName(description));
        if (!digitalsignaturealgorithm.isSupported()) {
            throw new NoSuchElementException(
                String.format("DigitalSignatureAlgorithm '%s' not supported for spec %s", description, spec));
        }

        return digitalsignaturealgorithm;
    }
}
