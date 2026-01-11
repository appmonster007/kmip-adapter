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
import org.purpleBean.kmip.common.MaximumResponseSize;

import java.io.IOException;

public class MaximumResponseSizeXmlDeserializer extends KmipDataTypeXmlDeserializer<MaximumResponseSize> {
    private final KmipTag kmipTag = MaximumResponseSize.kmipTag;
    private final EncodingType encodingType = MaximumResponseSize.encodingType;

    @Override
    public MaximumResponseSize deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(MaximumResponseSize.class, "Invalid Tag for MaximumResponseSize");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        MaximumResponseSize.MaximumResponseSizeBuilder builder = MaximumResponseSize.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(MaximumResponseSize.class, "Missing or invalid 'type' attribute for MaximumResponseSize");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(MaximumResponseSize.class,
                                "Missing or non-number 'value' for MaximumResponseSize");
                        return null;
                    }
                    builder.value(Integer.parseInt(p.getText()));
                }
            }
        }

        MaximumResponseSize maximumResponseSize = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!maximumResponseSize.isSupported()) {
            ctxt.reportInputMismatch(MaximumResponseSize.class, "MaximumResponseSize not supported for spec " + spec);
            return null;
        }

        return maximumResponseSize;
    }
}