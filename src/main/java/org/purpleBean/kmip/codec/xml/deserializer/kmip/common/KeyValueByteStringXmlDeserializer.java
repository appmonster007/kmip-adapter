package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
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
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(KeyValue.ByteString.class, "Invalid Tag for KeyValue.ByteString");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KeyValue.ByteString.ByteStringBuilder builder = KeyValue.ByteString.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(KeyValue.ByteString.class, "Missing or invalid 'type' attribute for KeyValue.ByteString");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(KeyValue.ByteString.class,
                                "Missing or non-text 'value' for KeyValue.ByteString");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, ByteBuffer.class));
                }
            }
        }

        KeyValue.ByteString keyValueByteString = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!keyValueByteString.isSupported()) {
            ctxt.reportInputMismatch(KeyValue.ByteString.class, "KeyValue.ByteString not supported for spec " + spec);
            return null;
        }

        return keyValueByteString;
    }
}