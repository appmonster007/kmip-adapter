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
import org.purpleBean.kmip.common.KeyValuePresent;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for KeyValuePresent.
 */
public class KeyValuePresentXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyValuePresent> {
    private final KmipTag kmipTag = KeyValuePresent.kmipTag;
    private final EncodingType encodingType = KeyValuePresent.encodingType;

    @Override
    public KeyValuePresent deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(KeyValuePresent.class, "Expected XML element object for KeyValuePresent");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(KeyValuePresent.class, "Invalid Tag for KeyValuePresent");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(KeyValuePresent.class, "Missing or invalid '@type' attribute for KeyValuePresent");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyValuePresent.class, "Missing or non-text '@value' attribute for KeyValuePresent");
            return null;
        }

        String value = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        KeyValuePresent attribute = KeyValuePresent.of(Boolean.valueOf(value));
        if (!attribute.isSupported()) {
            throw new NoSuchElementException(
                    String.format("KeyValuePresent not supported for spec %s", spec));
        }

        return attribute;
    }
}