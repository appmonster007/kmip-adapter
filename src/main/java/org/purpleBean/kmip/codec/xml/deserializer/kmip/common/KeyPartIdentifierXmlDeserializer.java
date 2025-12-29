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
import org.purpleBean.kmip.common.KeyPartIdentifier;

import java.io.IOException;

public class KeyPartIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyPartIdentifier> {
    private final KmipTag kmipTag = KeyPartIdentifier.kmipTag;
    private final EncodingType encodingType = KeyPartIdentifier.encodingType;

    @Override
    public KeyPartIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(KeyPartIdentifier.class, "Expected XML object for KeyPartIdentifier");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(KeyPartIdentifier.class, "Invalid Tag for KeyPartIdentifier");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(KeyPartIdentifier.class, "Missing or invalid '@type' attribute for KeyPartIdentifier");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyPartIdentifier.class,
                    "Missing or non-number 'value' for KeyPartIdentifier");
            return null;
        }

        int value = Integer.parseInt(valueNode.asText());
        KeyPartIdentifier keyPartIdentifier = KeyPartIdentifier.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!keyPartIdentifier.isSupported()) {
            ctxt.reportInputMismatch(KeyPartIdentifier.class, "KeyPartIdentifier not supported for spec " + spec);
            return null;
        }

        return keyPartIdentifier;
    }
}