package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.structure.Attribute;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

/**
 * JSON deserializer for CustomAttribute.
 */
public class CustomAttributeJsonDeserializer extends KmipDataTypeJsonDeserializer<Attribute.CustomAttribute> {

    @Override
    public Attribute.CustomAttribute deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        JsonNode nameNode = node.get("name");
        if (nameNode == null || !nameNode.isTextual() || !Attribute.isCustomAttribute(nameNode.asText())) {
            throw new IllegalArgumentException("Missing or non-text 'name' field for CustomAttribute");
        }
        String name = nameNode.asText();

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() || EncodingType.fromName(typeNode.asText()).isEmpty()) {
            throw new IllegalArgumentException("Missing or non-text 'type' field for CustomAttribute");
        }

        JsonNode valueNode = node.get("value");

        EncodingType encodingType = EncodingType.fromName(typeNode.asText()).get();
        Object obj;

        switch (encodingType) {
            case INTEGER, ENUMERATION, INTERVAL -> obj = valueNode.intValue();
            case BOOLEAN -> obj = valueNode.asBoolean();
            case DATE_TIME -> obj = OffsetDateTime.parse(valueNode.asText());
            case LONG_INTEGER -> obj = valueNode.longValue();
            case TEXT_STRING -> obj = valueNode.asText();
            case BYTE_STRING -> obj = ByteBuffer.wrap(valueNode.asText().getBytes());
//            case BIG_INTEGER -> o = valueNode.asText();
            default -> throw new IllegalArgumentException("Unsupported encoding type: " + encodingType);
        }

        return Attribute.CustomAttribute.of(name, encodingType, obj);
    }
}
