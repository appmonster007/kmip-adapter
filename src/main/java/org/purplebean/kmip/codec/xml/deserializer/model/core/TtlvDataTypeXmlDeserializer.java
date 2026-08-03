package org.purplebean.kmip.codec.xml.deserializer.model.core;

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
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.TtlvDataType;

/**
 * XML deserializer for {@link TtlvDataType}.
 */
public class TtlvDataTypeXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<TtlvDataType, TtlvDataType.TtlvDataTypeBuilder> {

  private final Stack<KmipTag.Value> kmipTagStack = new Stack<>();
  private final Stack<EncodingType> encodingTypeStack = new Stack<>();
  private final Stack<Object> valueStack = new Stack<>();

  /**
   * Constructs a new {@link TtlvDataTypeXmlDeserializer}.
   */
  public TtlvDataTypeXmlDeserializer() {
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
  protected String getTag(String xmlTagName, JsonNode node, DeserializationContext ctxt,
                          TtlvDataType.TtlvDataTypeBuilder builder) throws IOException {
    String tag = xmlTagName;
    String name = null;

    if ("TTLV".equals(tag) && node.has("tag")) {
      tag = node
          .get("tag")
          .asText();
    }

    // If tag is still null or empty, try to get from node if possible (though xmlTagName should
    // be populated)
    if (tag == null || tag.isEmpty()) {
      if (node.has("tag")) {
        tag = node
            .get("tag")
            .asText();
      } else if (node.has("name")) {
        name = node
            .get("name")
            .asText();
        // If we have name but no tag, we might infer tag from name if it's a standard KMIP tag
        tag = name;
      }
    }

    if (tag == null) {
      ctxt.reportInputMismatch(KmipTag.class, "Could not determine tag");
      return null;
    }

    KmipTag.Value nodeTag;
    try {
      nodeTag = KmipTag.fromName(tag);
    } catch (NoSuchElementException e) {
      // Register Tag if unknown
      String hexTag = tag;
      if (hexTag.matches("^[0-9][xX].*")) {
        hexTag = hexTag.replaceFirst("^[0-9][xX]", "");
      }
      try {
        byte[] tagBytes = HexFormat
            .of()
            .parseHex(hexTag);
        nodeTag = KmipTag.register(
            tagBytes,
            name,
            Stream
                .of(KmipSpec.UnknownVersion, KmipContext.getSpec())
                .collect(Collectors.toSet())
        );
      } catch (IllegalArgumentException ex) {
        // If not hex, maybe it's a custom name that isn't registered yet?
        // For now, rethrow or handle as error
        throw new IllegalArgumentException("Unknown tag and not valid hex: " + tag, e);
      }
    }
    kmipTagStack.push(nodeTag);
    builder.kmipTag(nodeTag.inst());

    return tag;
  }

  @Override
  protected String getType(JsonNode node, DeserializationContext ctxt,
                           TtlvDataType.TtlvDataTypeBuilder builder) throws IOException {
    JsonNode typeNode = node.get("type");
    String type;
    if (typeNode == null || !typeNode.isTextual()) {
      type = EncodingType.STRUCTURE.getDescription();
    } else {
      type = typeNode.asText();
    }

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