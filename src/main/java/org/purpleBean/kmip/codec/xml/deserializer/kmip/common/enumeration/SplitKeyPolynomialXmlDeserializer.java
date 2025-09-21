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
import org.purpleBean.kmip.common.enumeration.SplitKeyPolynomial;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for SplitKeyPolynomial.
 */
public class SplitKeyPolynomialXmlDeserializer extends KmipDataTypeXmlDeserializer<SplitKeyPolynomial> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.SPLIT_KEY_POLYNOMIAL);

    @Override
    public SplitKeyPolynomial deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(SplitKeyPolynomial.class, "Expected XML element object for SplitKeyPolynomial");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(SplitKeyPolynomial.class, "Invalid Tag for SplitKeyPolynomial");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(SplitKeyPolynomial.class, "Missing or invalid '@type' attribute for SplitKeyPolynomial");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(SplitKeyPolynomial.class, "Missing or non-text '@value' attribute for SplitKeyPolynomial");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        SplitKeyPolynomial splitkeypolynomial = new SplitKeyPolynomial(SplitKeyPolynomial.fromName(spec, description));
        if (!splitkeypolynomial.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("SplitKeyPolynomial '%s' not supported for spec %s", description, spec));
        }

        return splitkeypolynomial;
    }
}
