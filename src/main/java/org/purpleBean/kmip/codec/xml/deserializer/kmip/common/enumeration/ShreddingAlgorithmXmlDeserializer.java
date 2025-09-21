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
import org.purpleBean.kmip.common.enumeration.ShreddingAlgorithm;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for ShreddingAlgorithm.
 */
public class ShreddingAlgorithmXmlDeserializer extends KmipDataTypeXmlDeserializer<ShreddingAlgorithm> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.SHREDDING_ALGORITHM);

    @Override
    public ShreddingAlgorithm deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ShreddingAlgorithm.class, "Expected XML element object for ShreddingAlgorithm");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ShreddingAlgorithm.class, "Invalid Tag for ShreddingAlgorithm");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ShreddingAlgorithm.class, "Missing or invalid '@type' attribute for ShreddingAlgorithm");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ShreddingAlgorithm.class, "Missing or non-text '@value' attribute for ShreddingAlgorithm");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        ShreddingAlgorithm shreddingalgorithm = new ShreddingAlgorithm(ShreddingAlgorithm.fromName(spec, description));
        if (!shreddingalgorithm.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("ShreddingAlgorithm '%s' not supported for spec %s", description, spec));
        }

        return shreddingalgorithm;
    }
}
