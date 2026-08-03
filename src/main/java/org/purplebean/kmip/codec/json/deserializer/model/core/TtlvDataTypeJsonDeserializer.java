package org.purplebean.kmip.codec.json.deserializer.model.core;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipEnumeration;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.TtlvDataType;

/**
 * JSON deserializer for {@link TtlvDataType}.
 */
public class TtlvDataTypeJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<TtlvDataType, TtlvDataType.TtlvDataTypeBuilder> {

  private final Stack<KmipTag.Value> kmipTagStack = new Stack<>();
  private final Stack<EncodingType> encodingTypeStack = new Stack<>();
  private final Stack<Object> valueStack = new Stack<>();

  /**
   * Constructs a new {@link TtlvDataTypeJsonDeserializer}.
   */
  public TtlvDataTypeJsonDeserializer() {
    super(null, null);
  }

  @Override
  protected TtlvDataType.TtlvDataTypeBuilder createBuilder() {
    return TtlvDataType.builder();
  }

  @Override
  protected void setValue(TtlvDataType.TtlvDataTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonNode node = p
        .getCodec()
        .readTree(p);

    EncodingType encodingType = encodingTypeStack.peek();
    switch (encodingType) {
      case INTEGER -> valueStack.push(ctxt.readTreeAsValue(node, Integer.class));
      case LONG_INTEGER -> valueStack.push(ctxt.readTreeAsValue(node, Long.class));
      case BIG_INTEGER -> valueStack.push(ctxt.readTreeAsValue(node, BigInteger.class));
      case BOOLEAN -> valueStack.push(ctxt.readTreeAsValue(node, Boolean.class));
      case TEXT_STRING -> valueStack.push(ctxt.readTreeAsValue(node, String.class));
      case BYTE_STRING -> valueStack.push(ctxt.readTreeAsValue(node, ByteBuffer.class));
      case DATE_TIME -> valueStack.push(ctxt.readTreeAsValue(node, OffsetDateTime.class));
      case INTERVAL -> valueStack.push(ctxt.readTreeAsValue(node, Integer.class));
      case ENUMERATION -> {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        var factory = KmipEnumeration.getFromName(nodeTag);
        String value = ctxt.readTreeAsValue(node, String.class);
        if (factory == null) {
          throw new IllegalArgumentException(
              String.format("Invalid value [%s] for enumeration tag %s", value,
                  nodeTag.getDescription()));
        }
        valueStack.push(factory.apply(value));
      }
      case STRUCTURE -> {
        if (valueStack.peek() instanceof List<?>) {
          List<KmipDataType> valueList = (List<KmipDataType>) valueStack.peek();

          if (!node.has("tag")) {
            return;
          }
          if (!node.has("type")) {
            return;
          }
          valueList.add(ctxt.readTreeAsValue(node, TtlvDataType.class));
        }
      }
      default -> throw new IllegalArgumentException("Unsupported encoding type: " + encodingType);
    }
  }

  @Override
  protected TtlvDataType build(TtlvDataType.TtlvDataTypeBuilder builder) {
    KmipTag.Value nodeTag = kmipTagStack.pop();
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
  protected String getTag(JsonNode node, DeserializationContext ctxt,
                          TtlvDataType.TtlvDataTypeBuilder builder) throws IOException {
    JsonNode nameNode = node.get("name");
    JsonNode tagNode = node.get("tag");

    String tag = null;
    String name = null;
    if (tagNode != null && tagNode.isTextual()) {
      tag = tagNode.asText();
    }

    if (nameNode != null && nameNode.isTextual()) {
      name = nameNode.asText();
    }

    if (tag == null) {
      ctxt.reportInputMismatch(KmipTag.class,
          "Expected 'name' or 'tag' field with string value in object");
      return null;
    }

    KmipTag.Value nodeTag;
    try {
      nodeTag = KmipTag.fromName(tag);
    } catch (NoSuchElementException e) {
      // Register Tag if unknown
      if (tag.matches("^[0-9][xX].*")) {
        tag = tag.replaceFirst("^[0-9][xX]", "");
      }
      byte[] tagBytes = HexFormat
          .of()
          .parseHex(tag);
      nodeTag = KmipTag.register(
          tagBytes,
          name,
          Stream
              .of(KmipSpec.UnknownVersion, KmipContext.getSpec())
              .collect(Collectors.toSet())
      );
    }
    kmipTagStack.push(nodeTag);
    builder.kmipTag(nodeTag.inst());

    return tag;
  }

  @Override
  protected String getType(JsonNode node, DeserializationContext ctxt,
                           TtlvDataType.TtlvDataTypeBuilder builder) throws IOException {
    JsonNode typeNode = node.get("type");
    if (typeNode == null || !typeNode.isTextual()) {
      ctxt.reportInputMismatch(handledType(), "Missing or invalid 'type' field");
      return null;
    }

    String type = typeNode.asText();
    EncodingType encodingType = EncodingType
        .fromName(type)
        .orElseThrow(
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
