package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipEnumeration;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueEnumeration;
import org.purpleBean.kmip.util.StringUtils;

import java.io.IOException;

public class AttributeValueEnumerationJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueEnumeration, Integer> {

    public AttributeValueEnumerationJsonDeserializer() {
        super(AttributeValueEnumeration.kmipTag, AttributeValueEnumeration.encodingType, Integer.class, null);
    }

    @Override
    public AttributeValueEnumeration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = ctxt.readTree(p);

        if (node == null) {
            ctxt.reportInputMismatch(handledType(), "JSON node cannot be null");
            return null;
        }

        KmipTag tag;
        try {
            tag = ctxt.readTreeAsValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(handledType(), "Invalid KMIP tag");
                return null;
            }
        } catch (Exception e) {
            ctxt.handleWeirdStringValue(handledType(), node.toString(), "Failed to parse KMIP tag: " + e.getMessage());
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != AttributeValueEnumeration.kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(handledType(), "Expected object with tag " + AttributeValueEnumeration.kmipTag.getValue().getValue());
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || !AttributeValueEnumeration.encodingType.getDescription().equals(typeNode.asText())
        ) {
            ctxt.reportInputMismatch(handledType(), "Missing or invalid 'type' field");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null) {
            ctxt.reportInputMismatch(handledType(), "Missing 'value' field");
            return null;
        }

        String name = valueNode.asText();

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