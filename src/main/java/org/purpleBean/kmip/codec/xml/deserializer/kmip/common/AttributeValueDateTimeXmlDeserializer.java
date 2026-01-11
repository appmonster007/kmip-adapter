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
import org.purpleBean.kmip.common.AttributeValueDateTime;

import java.io.IOException;
import java.time.OffsetDateTime;

public class AttributeValueDateTimeXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValueDateTime> {
    private final KmipTag kmipTag = AttributeValueDateTime.kmipTag;
    private final EncodingType encodingType = AttributeValueDateTime.encodingType;

    @Override
    public AttributeValueDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(AttributeValueDateTime.class, "Invalid Tag for AttributeValue.DateTime");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        AttributeValueDateTime.AttributeValueDateTimeBuilder builder = AttributeValueDateTime.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(AttributeValueDateTime.class, "Missing or invalid 'type' attribute for AttributeValue.DateTime");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(AttributeValueDateTime.class,
                                "Missing or non-text 'value' for AttributeValue.DateTime");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, OffsetDateTime.class));
                }
            }
        }

        AttributeValueDateTime attributeValueDateTime = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueDateTime.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueDateTime.class, "AttributeValue.DateTime not supported for spec " + spec);
            return null;
        }

        return attributeValueDateTime;
    }
}