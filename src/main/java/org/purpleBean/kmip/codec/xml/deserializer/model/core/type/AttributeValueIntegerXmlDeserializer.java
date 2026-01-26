package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipMaskType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;
import org.purpleBean.kmip.util.StringUtils;

import java.io.IOException;
import java.util.function.Function;

public class AttributeValueIntegerXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueInteger, Integer> {

    public AttributeValueIntegerXmlDeserializer() {
        super(AttributeValueInteger.kmipTag, AttributeValueInteger.encodingType, Integer.class, value -> AttributeValueInteger.builder().value(value).build());
    }

    @Override
    public AttributeValueInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!AttributeValueInteger.kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(handledType(), "Invalid Tag for " + handledType().getSimpleName());
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        String value = null;

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!AttributeValueInteger.encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(handledType(), "Missing or invalid 'type' attribute for " + handledType().getSimpleName());
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    value = p.readValueAs(String.class);
                }
            }
        }

        if (value == null) {
            ctxt.reportInputMismatch(handledType(), "Missing 'value' for " + handledType().getSimpleName());
            return null;
        }

        AttributeValueInteger result;
        String attributeName = (String) ctxt.getAttribute("attributeName");
        if (attributeName != null && attributeName.toLowerCase().contains("mask")) {
            KmipTag.Value kmipTag = KmipTag.fromName(StringUtils.covertTitleToPascalCase(attributeName));
            Function<String, ? extends KmipMaskType> fromMaskString = KmipMaskType.getFromMaskString(kmipTag);
            result = AttributeValueInteger.builder()
                    .value(fromMaskString.apply(value).getValue())
                    .maskStringValue(value)
                    .build();
        } else {
            result = AttributeValueInteger.builder().value(Integer.parseInt(value)).build();
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!result.isSupported()) {
            ctxt.reportInputMismatch(handledType(), String.format("%s not supported for spec %s", handledType().getSimpleName(), spec));
            return null;
        }

        return result;
    }
}