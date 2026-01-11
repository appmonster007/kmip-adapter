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
import org.purpleBean.kmip.common.RevocationMessage;

import java.io.IOException;

public class RevocationMessageXmlDeserializer extends KmipDataTypeXmlDeserializer<RevocationMessage> {
    private final KmipTag kmipTag = RevocationMessage.kmipTag;
    private final EncodingType encodingType = RevocationMessage.encodingType;

    @Override
    public RevocationMessage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(RevocationMessage.class, "Invalid Tag for RevocationMessage");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        RevocationMessage.RevocationMessageBuilder builder = RevocationMessage.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(RevocationMessage.class, "Missing or invalid 'type' attribute for RevocationMessage");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(RevocationMessage.class,
                                "Missing or non-text 'value' for RevocationMessage");
                        return null;
                    }
                    builder.value(p.getText());
                }
            }
        }

        RevocationMessage revocationMessage = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!revocationMessage.isSupported()) {
            ctxt.reportInputMismatch(RevocationMessage.class, "RevocationMessage not supported for spec " + spec);
            return null;
        }

        return revocationMessage;
    }
}