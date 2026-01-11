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
import org.purpleBean.kmip.common.NameValue;

import java.io.IOException;

public class NameValueXmlDeserializer extends KmipDataTypeXmlDeserializer<NameValue> {
    private final KmipTag kmipTag = NameValue.kmipTag;
    private final EncodingType encodingType = NameValue.encodingType;

    @Override
    public NameValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(NameValue.class, "Invalid Tag for NameValue");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        NameValue.NameValueBuilder builder = NameValue.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(NameValue.class, "Missing or invalid 'type' attribute for NameValue");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(NameValue.class,
                                "Missing or non-text 'value' for NameValue");
                        return null;
                    }
                    builder.value(p.getText());
                }
            }
        }

        NameValue nameValue = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!nameValue.isSupported()) {
            ctxt.reportInputMismatch(NameValue.class, "NameValue not supported for spec " + spec);
            return null;
        }

        return nameValue;
    }
}
