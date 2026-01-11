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
import org.purpleBean.kmip.common.ExtensionTag;

import java.io.IOException;

public class ExtensionTagXmlDeserializer extends KmipDataTypeXmlDeserializer<ExtensionTag> {
    private final KmipTag kmipTag = ExtensionTag.kmipTag;
    private final EncodingType encodingType = ExtensionTag.encodingType;

    @Override
    public ExtensionTag deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(ExtensionTag.class, "Invalid Tag for ExtensionTag");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        ExtensionTag.ExtensionTagBuilder builder = ExtensionTag.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(ExtensionTag.class, "Missing or invalid 'type' attribute for ExtensionTag");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(ExtensionTag.class,
                                "Missing or non-number 'value' for ExtensionTag");
                        return null;
                    }
                    builder.value(Integer.parseInt(p.getText()));
                }
            }
        }

        ExtensionTag extensionTag = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!extensionTag.isSupported()) {
            ctxt.reportInputMismatch(ExtensionTag.class, "ExtensionTag not supported for spec " + spec);
            return null;
        }

        return extensionTag;
    }
}