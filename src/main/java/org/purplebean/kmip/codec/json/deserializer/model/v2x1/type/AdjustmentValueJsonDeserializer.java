package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Stack;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.AdjustmentValue;

/**
 * JSON deserializer for {@link AdjustmentValue}.
 */
public class AdjustmentValueJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AdjustmentValue, AdjustmentValue.AdjustmentValueBuilder> {

  private final Stack<EncodingType> encodingTypeStack = new Stack<>();
  private final Stack<Object> valueStack = new Stack<>();

  /**
   * Constructs a new {@link AdjustmentValueJsonDeserializer}.
   */
  public AdjustmentValueJsonDeserializer() {
    super(AdjustmentValue.kmipTag, null);
  }

  @Override
  protected AdjustmentValue.AdjustmentValueBuilder createBuilder() {
    return AdjustmentValue.builder();
  }

  @Override
  protected void setValue(AdjustmentValue.AdjustmentValueBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonNode node = p
        .getCodec()
        .readTree(p);

    EncodingType encodingType = encodingTypeStack.peek();
    switch (encodingType) {
      case INTEGER -> valueStack.push(ctxt.readTreeAsValue(node, Integer.class));
      case LONG_INTEGER -> valueStack.push(ctxt.readTreeAsValue(node, Long.class));
      case BIG_INTEGER -> valueStack.push(ctxt.readTreeAsValue(node, BigInteger.class));
      case BYTE_STRING -> valueStack.push(ctxt.readTreeAsValue(node, ByteBuffer.class));
      case DATE_TIME -> valueStack.push(ctxt.readTreeAsValue(node, OffsetDateTime.class));
      case INTERVAL -> valueStack.push(ctxt.readTreeAsValue(node, Integer.class));
      default -> throw new IllegalArgumentException("Unsupported encoding type: " + encodingType);
    }
  }

  @Override
  protected AdjustmentValue build(AdjustmentValue.AdjustmentValueBuilder builder) {
    encodingTypeStack.pop();
    builder.value(valueStack.pop());
    return builder.build();
  }

  @Override
  protected String getType(JsonNode node, DeserializationContext ctxt,
                           AdjustmentValue.AdjustmentValueBuilder builder) throws IOException {
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
    builder.encodingType(encodingType);

    return type;
  }
}
