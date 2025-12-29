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
import org.purpleBean.kmip.common.Username;

import java.io.IOException;

public class UsernameXmlDeserializer extends KmipDataTypeXmlDeserializer<Username> {
    private final KmipTag kmipTag = Username.kmipTag;
    private final EncodingType encodingType = Username.encodingType;

    @Override
    public Username deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(Username.class, "Expected XML object for Username");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(Username.class, "Invalid Tag for Username");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(Username.class, "Missing or invalid '@type' attribute for Username");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Username.class,
                    "Missing or non-text 'value' for Username");
            return null;
        }

        String value = valueNode.asText();
        Username username = Username.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!username.isSupported()) {
            ctxt.reportInputMismatch(Username.class, "Username not supported for spec " + spec);
            return null;
        }

        return username;
    }
}