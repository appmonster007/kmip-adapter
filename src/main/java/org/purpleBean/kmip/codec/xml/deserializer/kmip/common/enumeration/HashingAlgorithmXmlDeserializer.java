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
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for HashingAlgorithm.
 */
public class HashingAlgorithmXmlDeserializer extends KmipDataTypeXmlDeserializer<HashingAlgorithm> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.HASHING_ALGORITHM);

    @Override
    public HashingAlgorithm deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(HashingAlgorithm.class, "Expected XML element object for HashingAlgorithm");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(HashingAlgorithm.class, "Invalid Tag for HashingAlgorithm");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(HashingAlgorithm.class, "Missing or invalid '@type' attribute for HashingAlgorithm");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(HashingAlgorithm.class, "Missing or non-text '@value' attribute for HashingAlgorithm");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        HashingAlgorithm hashingalgorithm = new HashingAlgorithm(HashingAlgorithm.fromName(spec, description));
        if (!hashingalgorithm.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("HashingAlgorithm '%s' not supported for spec %s", description, spec));
        }

        return hashingalgorithm;
    }
}
