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
import org.purpleBean.kmip.common.structure.Attribute;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for AttributeIndex.
 */
public class AttributeIndexXmlDeserializer extends KmipDataTypeXmlDeserializer<Attribute.AttributeIndex> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE_INDEX);
    private final EncodingType encodingType = EncodingType.INTEGER;

    @Override
    public Attribute.AttributeIndex deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(Attribute.AttributeIndex.class, "Expected XML element object for AttributeIndex");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(Attribute.AttributeIndex.class, "Invalid Tag for AttributeIndex");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(Attribute.AttributeIndex.class, "Missing or invalid '@type' datatype for AttributeIndex");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Attribute.AttributeIndex.class,
                    "Missing or non-text 'value' for AttributeIndex");
            return null;
        }

        int index = valueNode.asInt();
        Attribute.AttributeIndex datatype = Attribute.AttributeIndex.of(index);

        KmipSpec spec = KmipContext.getSpec();
        if (!datatype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("AttributeIndex '%s' not supported for spec %s", kmipTag.getDescription(), spec));

        }
        return datatype;
    }
}
