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
import org.purpleBean.kmip.common.enumeration.ObjectType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for ObjectType.
 */
public class ObjectTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<ObjectType> {
    private final KmipTag kmipTag = ObjectType.kmipTag;
    private final EncodingType encodingType = ObjectType.encodingType;

    @Override
    public ObjectType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ObjectType.class, "Expected XML element object for ObjectType");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ObjectType.class, "Invalid Tag for ObjectType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ObjectType.class, "Missing or invalid '@type' attribute for ObjectType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ObjectType.class, "Missing or non-text '@value' attribute for ObjectType");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        ObjectType objecttype = new ObjectType(ObjectType.fromName(description));
        if (!objecttype.isSupported()) {
            throw new NoSuchElementException(
                String.format("ObjectType '%s' not supported for spec %s", description, spec));
        }

        return objecttype;
    }
}
