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
import org.purpleBean.kmip.common.enumeration.SplitKeyMethod;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for SplitKeyMethod.
 */
public class SplitKeyMethodXmlDeserializer extends KmipDataTypeXmlDeserializer<SplitKeyMethod> {
    private final KmipTag kmipTag = SplitKeyMethod.kmipTag;
    private final EncodingType encodingType = SplitKeyMethod.encodingType;

    @Override
    public SplitKeyMethod deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(SplitKeyMethod.class, "Expected XML element object for SplitKeyMethod");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(SplitKeyMethod.class, "Invalid Tag for SplitKeyMethod");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(SplitKeyMethod.class, "Missing or invalid '@type' attribute for SplitKeyMethod");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(SplitKeyMethod.class, "Missing or non-text '@value' attribute for SplitKeyMethod");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        SplitKeyMethod splitkeymethod = new SplitKeyMethod(SplitKeyMethod.fromName(description));
        if (!splitkeymethod.isSupported()) {
            throw new NoSuchElementException(
                String.format("SplitKeyMethod '%s' not supported for spec %s", description, spec));
        }

        return splitkeymethod;
    }
}
