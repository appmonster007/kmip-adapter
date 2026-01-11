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
import org.purpleBean.kmip.common.ExtensionType;

import java.io.IOException;

public class ExtensionTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<ExtensionType> {
    private final KmipTag kmipTag = ExtensionType.kmipTag;
    private final EncodingType encodingType = ExtensionType.encodingType;

    @Override
    public ExtensionType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(ExtensionType.class, "Invalid Tag for ExtensionType");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        ExtensionType.ExtensionTypeBuilder builder = ExtensionType.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(ExtensionType.class, "Missing or invalid 'type' attribute for ExtensionType");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(ExtensionType.class,
                                "Missing or non-number 'value' for ExtensionType");
                        return null;
                    }
                    builder.value(Integer.parseInt(p.getText()));
                }
            }
        }

        ExtensionType extensionType = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!extensionType.isSupported()) {
            ctxt.reportInputMismatch(ExtensionType.class, "ExtensionType not supported for spec " + spec);
            return null;
        }

        return extensionType;
    }
}