package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;

import java.io.IOException;
import java.util.NoSuchElementException;

public class AttributeValueXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValue> {

    private final KmipTag kmipTag = AttributeValue.kmipTag;

    @Override
    public AttributeValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttributeValue.class, "Expected XML object for AttributeValue");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AttributeValue.class, "Invalid Tag for AttributeValue");
            return null;
        }

        JsonNode typeNode = node.get("type");
        EncodingType encodingType;
        if (typeNode == null) {
            encodingType = EncodingType.STRUCTURE;
        } else {
            encodingType = EncodingType.fromName(typeNode.asText()).get();
        }

        KmipSpec spec = KmipContext.getSpec();
        Class<? extends KmipDataType> clazz = KmipDataType.getClassFromRegistry(kmipTag.getValue(), encodingType);
        if (clazz == null) {
            throw new NoSuchElementException(String.format("No class registered for tag %s and encoding type %s", kmipTag.getValue(), encodingType));
        }

        AttributeValue attributeValue = (AttributeValue) p.getCodec().treeToValue(node, clazz);

        if (!attributeValue.isSupported()) {
            ctxt.reportInputMismatch(AttributeValue.class, "AttributeValue not supported for spec " + spec);
            return null;
        }

        return attributeValue;
    }
}
