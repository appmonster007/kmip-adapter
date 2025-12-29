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
import org.purpleBean.kmip.common.KeyValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyValueByteStringXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyValue.ByteString> {
    private final KmipTag kmipTag = KeyValue.ByteString.kmipTag;
    private final EncodingType encodingType = KeyValue.ByteString.encodingType;

    @Override
    public KeyValue.ByteString deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(KeyValue.ByteString.class, "Expected XML object for KeyValue.ByteString");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(KeyValue.ByteString.class, "Invalid Tag for KeyValue.ByteString");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(KeyValue.ByteString.class, "Missing or invalid '@type' attribute for KeyValue.ByteString");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyValue.ByteString.class,
                    "Missing or non-text 'value' for KeyValue.ByteString");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        KeyValue.ByteString keyValueByteString = KeyValue.ByteString.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!keyValueByteString.isSupported()) {
            ctxt.reportInputMismatch(KeyValue.ByteString.class, "KeyValue.ByteString not supported for spec " + spec);
            return null;
        }

        return keyValueByteString;
    }
}