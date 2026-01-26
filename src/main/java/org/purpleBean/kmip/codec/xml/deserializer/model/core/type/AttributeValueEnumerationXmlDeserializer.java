package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipEnumeration;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueEnumeration;
import org.purpleBean.kmip.util.StringUtils;

import java.io.IOException;

public class AttributeValueEnumerationXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueEnumeration, Integer> {

    public AttributeValueEnumerationXmlDeserializer() {
        super(AttributeValueEnumeration.kmipTag, AttributeValueEnumeration.encodingType, Integer.class, null);
    }

    @Override
    public AttributeValueEnumeration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!AttributeValueEnumeration.kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(handledType(), "Invalid Tag for " + handledType().getSimpleName());
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        String name = null;

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!AttributeValueEnumeration.encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(handledType(), "Missing or invalid 'type' attribute for " + handledType().getSimpleName());
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    name = p.readValueAs(String.class);
                }
            }
        }

        if (name == null) {
            ctxt.reportInputMismatch(handledType(), "Missing 'value' for " + handledType().getSimpleName());
            return null;
        }

        String attributeName = (String) ctxt.getAttribute("attributeName");
        KmipTag.Value enumType = KmipTag.fromName(StringUtils.covertTitleToPascalCase(attributeName));
        if (enumType == null) {
            ctxt.reportInputMismatch(handledType(), "Invalid attribute name for " + handledType().getSimpleName());
            return null;
        }
        KmipEnumeration.Value<?> value = KmipEnumeration.getFromName(enumType).apply(name);
        AttributeValueEnumeration result = AttributeValueEnumeration.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();
        if (!result.isSupported()) {
            ctxt.reportInputMismatch(handledType(), String.format("%s not supported for spec %s", handledType().getSimpleName(), spec));
            return null;
        }

        return result;
    }
}