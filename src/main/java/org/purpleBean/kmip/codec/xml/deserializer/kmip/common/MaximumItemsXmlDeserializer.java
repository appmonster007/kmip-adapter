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
import org.purpleBean.kmip.common.MaximumItems;

import java.io.IOException;

public class MaximumItemsXmlDeserializer extends KmipDataTypeXmlDeserializer<MaximumItems> {
    private final KmipTag kmipTag = MaximumItems.kmipTag;
    private final EncodingType encodingType = MaximumItems.encodingType;

    @Override
    public MaximumItems deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(MaximumItems.class, "Invalid Tag for MaximumItems");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        MaximumItems.MaximumItemsBuilder builder = MaximumItems.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(MaximumItems.class, "Missing or invalid 'type' attribute for MaximumItems");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(MaximumItems.class,
                                "Missing or non-number 'value' for MaximumItems");
                        return null;
                    }
                    builder.value(Integer.parseInt(p.getText()));
                }
            }
        }

        MaximumItems maximumItems = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!maximumItems.isSupported()) {
            ctxt.reportInputMismatch(MaximumItems.class, "MaximumItems not supported for spec " + spec);
            return null;
        }

        return maximumItems;
    }
}