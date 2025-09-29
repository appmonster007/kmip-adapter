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
import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for DrbgAlgorithm.
 */
public class DrbgAlgorithmXmlDeserializer extends KmipDataTypeXmlDeserializer<DrbgAlgorithm> {
    private final KmipTag kmipTag = DrbgAlgorithm.kmipTag;
    private final EncodingType encodingType = DrbgAlgorithm.encodingType;

    @Override
    public DrbgAlgorithm deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(DrbgAlgorithm.class, "Expected XML element object for DrbgAlgorithm");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(DrbgAlgorithm.class, "Invalid Tag for DrbgAlgorithm");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(DrbgAlgorithm.class, "Missing or invalid '@type' attribute for DrbgAlgorithm");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DrbgAlgorithm.class, "Missing or non-text '@value' attribute for DrbgAlgorithm");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        DrbgAlgorithm drbgalgorithm = new DrbgAlgorithm(DrbgAlgorithm.fromName(description));
        if (!drbgalgorithm.isSupported()) {
            throw new NoSuchElementException(
                String.format("DrbgAlgorithm '%s' not supported for spec %s", description, spec));
        }

        return drbgalgorithm;
    }
}
