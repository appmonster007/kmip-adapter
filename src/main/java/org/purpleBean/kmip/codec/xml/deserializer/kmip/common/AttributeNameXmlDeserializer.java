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
import org.purpleBean.kmip.common.AttributeName;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for AttributeName.
 */
public class AttributeNameXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeName> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE_NAME);
    private final EncodingType encodingType = EncodingType.TEXT_STRING;

    @Override
    public AttributeName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttributeName.class, "Expected XML element object for AttributeName");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AttributeName.class, "Invalid Tag for AttributeName");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AttributeName.class, "Missing or invalid '@type' datatype for AttributeName");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeName.class,
                    "Missing or non-text 'value' for AttributeName");
            return null;
        }

        String name = valueNode.asText();
        AttributeName datatype = AttributeName.of(name);

        KmipSpec spec = KmipContext.getSpec();
        if (!datatype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("AttributeName '%s' not supported for spec %s", kmipTag.getDescription(), spec));

        }
        return datatype;
    }
}
