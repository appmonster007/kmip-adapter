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
import org.purpleBean.kmip.common.RevocationMessage;

import java.io.IOException;

public class RevocationMessageXmlDeserializer extends KmipDataTypeXmlDeserializer<RevocationMessage> {
    private final KmipTag kmipTag = RevocationMessage.kmipTag;
    private final EncodingType encodingType = RevocationMessage.encodingType;

    @Override
    public RevocationMessage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(RevocationMessage.class, "Expected XML object for RevocationMessage");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(RevocationMessage.class, "Invalid Tag for RevocationMessage");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(RevocationMessage.class, "Missing or invalid '@type' attribute for RevocationMessage");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(RevocationMessage.class,
                    "Missing or non-text 'value' for RevocationMessage");
            return null;
        }

        String value = valueNode.asText();
        RevocationMessage revocationMessage = RevocationMessage.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!revocationMessage.isSupported()) {
            ctxt.reportInputMismatch(RevocationMessage.class, "RevocationMessage not supported for spec " + spec);
            return null;
        }

        return revocationMessage;
    }
}