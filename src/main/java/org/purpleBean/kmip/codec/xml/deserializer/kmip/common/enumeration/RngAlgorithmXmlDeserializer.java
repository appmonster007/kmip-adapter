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
import org.purpleBean.kmip.common.enumeration.RngAlgorithm;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for RngAlgorithm.
 */
public class RngAlgorithmXmlDeserializer extends KmipDataTypeXmlDeserializer<RngAlgorithm> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.RNG_ALGORITHM);

    @Override
    public RngAlgorithm deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(RngAlgorithm.class, "Expected XML element object for RngAlgorithm");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(RngAlgorithm.class, "Invalid Tag for RngAlgorithm");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(RngAlgorithm.class, "Missing or invalid '@type' attribute for RngAlgorithm");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(RngAlgorithm.class, "Missing or non-text '@value' attribute for RngAlgorithm");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        RngAlgorithm rngalgorithm = new RngAlgorithm(RngAlgorithm.fromName(spec, description));
        if (!rngalgorithm.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("RngAlgorithm '%s' not supported for spec %s", description, spec));
        }

        return rngalgorithm;
    }
}
