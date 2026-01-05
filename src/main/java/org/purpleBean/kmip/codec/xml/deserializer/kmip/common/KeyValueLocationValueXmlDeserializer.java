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
import org.purpleBean.kmip.common.KeyValueLocationValue;

import java.io.IOException;

public class KeyValueLocationValueXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyValueLocationValue> {
    private final KmipTag kmipTag = KeyValueLocationValue.kmipTag;
    private final EncodingType encodingType = KeyValueLocationValue.encodingType;

    @Override
    public KeyValueLocationValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(KeyValueLocationValue.class, "Expected XML object for KeyValueLocationValue");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(KeyValueLocationValue.class, "Invalid Tag for KeyValueLocationValue");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(KeyValueLocationValue.class, "Missing or invalid '@type' attribute for KeyValueLocationValue");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyValueLocationValue.class,
                    "Missing or non-text 'value' for KeyValueLocationValue");
            return null;
        }

        KeyValueLocationValue keyValueLocationValue = KeyValueLocationValue.builder().value(valueNode.asText()).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!keyValueLocationValue.isSupported()) {
            ctxt.reportInputMismatch(KeyValueLocationValue.class, "KeyValueLocationValue not supported for spec " + spec);
            return null;
        }

        return keyValueLocationValue;
    }
}