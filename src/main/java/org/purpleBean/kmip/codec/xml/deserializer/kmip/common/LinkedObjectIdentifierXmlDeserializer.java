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
import org.purpleBean.kmip.common.LinkedObjectIdentifier;

import java.io.IOException;

public class LinkedObjectIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<LinkedObjectIdentifier> {
    private final KmipTag kmipTag = LinkedObjectIdentifier.kmipTag;
    private final EncodingType encodingType = LinkedObjectIdentifier.encodingType;

    @Override
    public LinkedObjectIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(LinkedObjectIdentifier.class, "Invalid Tag for LinkedObjectIdentifier");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        LinkedObjectIdentifier.LinkedObjectIdentifierBuilder builder = LinkedObjectIdentifier.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(LinkedObjectIdentifier.class, "Missing or invalid 'type' attribute for LinkedObjectIdentifier");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(LinkedObjectIdentifier.class,
                                "Missing or non-text 'value' for LinkedObjectIdentifier");
                        return null;
                    }
                    builder.value(p.getText());
                }
            }
        }

        LinkedObjectIdentifier linkedObjectIdentifier = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!linkedObjectIdentifier.isSupported()) {
            ctxt.reportInputMismatch(LinkedObjectIdentifier.class, "LinkedObjectIdentifier not supported for spec " + spec);
            return null;
        }

        return linkedObjectIdentifier;
    }
}