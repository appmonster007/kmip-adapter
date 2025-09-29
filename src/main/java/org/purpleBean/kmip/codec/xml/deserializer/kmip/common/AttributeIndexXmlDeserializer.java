package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

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
import org.purpleBean.kmip.common.AttributeIndex;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for AttributeIndex.
 */
public class AttributeIndexXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeIndex> {
    private final KmipTag kmipTag = AttributeIndex.kmipTag;
    private final EncodingType encodingType = AttributeIndex.encodingType;

    @Override
    public AttributeIndex deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttributeIndex.class, "Expected XML element object for AttributeIndex");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AttributeIndex.class, "Invalid Tag for AttributeIndex");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AttributeIndex.class, "Missing or invalid '@type' datatype for AttributeIndex");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeIndex.class,
                    "Missing or non-text 'value' for AttributeIndex");
            return null;
        }

        int index = valueNode.asInt();
        AttributeIndex attributeIndex = AttributeIndex.of(index);

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeIndex.isSupported()) {
            throw new NoSuchElementException(
                    String.format("AttributeIndex '%s' not supported for spec %s", kmipTag.getDescription(), spec));

        }
        return attributeIndex;
    }
}
