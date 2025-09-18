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
import org.purpleBean.kmip.common.enumeration.PutFunction;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for PutFunction.
 */
public class PutFunctionXmlDeserializer extends KmipDataTypeXmlDeserializer<PutFunction> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PUT_FUNCTION);

    @Override
    public PutFunction deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(PutFunction.class, "Expected XML element object for PutFunction");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(PutFunction.class, "Invalid Tag for PutFunction");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(PutFunction.class, "Missing or invalid '@type' attribute for PutFunction");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PutFunction.class, "Missing or non-text '@value' attribute for PutFunction");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        PutFunction putfunction = new PutFunction(PutFunction.fromName(spec, description));
        if (!putfunction.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("PutFunction '%s' not supported for spec %s", description, spec));
        }

        return putfunction;
    }
}
