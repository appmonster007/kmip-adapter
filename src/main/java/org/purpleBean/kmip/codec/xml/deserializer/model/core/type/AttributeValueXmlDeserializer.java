package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.util.StringUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

public class AttributeValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValue, AttributeValue.AttributeValueBuilder> {

    private final Stack<EncodingType> encodingTypeStack = new Stack<>();
    private final Stack<Object> valueStack = new Stack<>();

    public AttributeValueXmlDeserializer() {
        super(AttributeValue.kmipTag, null);
    }

    @Override
    protected AttributeValue.AttributeValueBuilder createBuilder() {
        return AttributeValue.builder();
    }

    @Override
    protected void setValue(AttributeValue.AttributeValueBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        EncodingType encodingType = encodingTypeStack.peek();
        switch (encodingType) {
            case INTEGER -> {
                String value = ctxt.readTreeAsValue(node, String.class);
                String attributeName = (String) ctxt.getAttribute("attributeName");
                if (attributeName != null && attributeName.toLowerCase().contains("mask")) {
                    KmipTag.Value kmipTag = KmipTag.fromName(StringUtils.convertTitleToPascalCase(attributeName));
                    Function<String, ? extends KmipMaskType> fromMaskString = KmipMaskType.getFromMaskString(kmipTag);
                    valueStack.push(fromMaskString.apply(value).getValue());
                    builder.maskString(value);
                } else {
                    valueStack.push(ctxt.readTreeAsValue(node, Integer.class));
                }
            }
            case LONG_INTEGER -> valueStack.push(ctxt.readTreeAsValue(node, Long.class));
            case BIG_INTEGER -> valueStack.push(ctxt.readTreeAsValue(node, BigInteger.class));
            case BOOLEAN -> valueStack.push(ctxt.readTreeAsValue(node, Boolean.class));
            case TEXT_STRING -> valueStack.push(ctxt.readTreeAsValue(node, String.class));
            case BYTE_STRING -> valueStack.push(ctxt.readTreeAsValue(node, ByteBuffer.class));
            case DATE_TIME -> valueStack.push(ctxt.readTreeAsValue(node, OffsetDateTime.class));
            case INTERVAL -> valueStack.push(ctxt.readTreeAsValue(node, Integer.class));
            case ENUMERATION -> {
                String attributeName = (String) ctxt.getAttribute("attributeName");
                KmipTag.Value nodeTag = KmipTag.fromName(StringUtils.convertTitleToPascalCase(attributeName));
                var factory = KmipEnumeration.getFromName(nodeTag);
                String value = ctxt.readTreeAsValue(node, String.class);
                if (factory == null) {
                    throw new IllegalArgumentException(String.format("Invalid value [%s] for enumeration tag %s", value, nodeTag.getDescription()));
                }
                valueStack.push(factory.apply(value));
            }
            case STRUCTURE -> {
                if (valueStack.peek() instanceof List<?>) {
                    List<KmipDataType> valueList = (List<KmipDataType>) valueStack.peek();
                    valueList.add(ctxt.readTreeAsValue(node, KmipDataType.class));
                }
            }
            default -> throw new IllegalArgumentException("Unsupported encoding type: " + encodingType);
        }
    }

    @Override
    protected AttributeValue build(AttributeValue.AttributeValueBuilder builder) {
        EncodingType encodingType = encodingTypeStack.pop();
        Object value = valueStack.pop();
        if (encodingType == EncodingType.STRUCTURE && value instanceof List<?> valueList) {
            builder.value(valueList.toArray(KmipDataType[]::new));
        } else {
            builder.value(value);
        }
        return builder.build();
    }

    @Override
    protected String getType(JsonNode node, DeserializationContext ctxt, AttributeValue.AttributeValueBuilder builder) throws IOException {
        JsonNode typeNode = node.get("type");
        String type;
        if (typeNode == null || !typeNode.isTextual()) {
            type = EncodingType.STRUCTURE.getDescription();
        } else {
            type = typeNode.asText();
        }

        EncodingType encodingType = EncodingType.fromName(type).orElseThrow(
                () -> new IllegalArgumentException("Unknown encoding type: " + type)
        );
        encodingTypeStack.push(encodingType);
        if (encodingType == EncodingType.STRUCTURE) {
            valueStack.push(new ArrayList<KmipDataType>());
        }
        builder.encodingType(encodingType);

        return type;
    }
}