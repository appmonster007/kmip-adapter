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
import org.purpleBean.kmip.common.enumeration.InteropFunction;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for InteropFunction.
 */
public class InteropFunctionXmlDeserializer extends KmipDataTypeXmlDeserializer<InteropFunction> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.INTEROP_FUNCTION);

    @Override
    public InteropFunction deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(InteropFunction.class, "Expected XML element object for InteropFunction");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(InteropFunction.class, "Invalid Tag for InteropFunction");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(InteropFunction.class, "Missing or invalid '@type' attribute for InteropFunction");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(InteropFunction.class, "Missing or non-text '@value' attribute for InteropFunction");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        InteropFunction interopfunction = new InteropFunction(InteropFunction.fromName(spec, description));
        if (!interopfunction.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("InteropFunction '%s' not supported for spec %s", description, spec));
        }

        return interopfunction;
    }
}
