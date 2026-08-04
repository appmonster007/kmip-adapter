package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.HexFormat;
import java.util.Stack;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.codec.ttlv.TtlvObject;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.AdjustmentValue;

/**
 * TTLV deserializer for {@link AdjustmentValue}.
 */
public class AdjustmentValueTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AdjustmentValue, AdjustmentValue.AdjustmentValueBuilder> {

  private final Stack<EncodingType> encodingTypeStack = new Stack<>();
  private final Stack<Object> valueStack = new Stack<>();

  /**
   * Constructs a new {@link AdjustmentValueTtlvDeserializer}.
   */
  public AdjustmentValueTtlvDeserializer() {
    super(AdjustmentValue.kmipTag, null);
  }

  @Override
  protected AdjustmentValue.AdjustmentValueBuilder createBuilder() {
    return AdjustmentValue.builder();
  }

  @Override
  protected void setValue(AdjustmentValue.AdjustmentValueBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    EncodingType encodingType = encodingTypeStack.peek();
    switch (encodingType) {
      case INTEGER -> valueStack.push(mapper.readValue(p, Integer.class));
      case LONG_INTEGER -> valueStack.push(mapper.readValue(p, Long.class));
      case BIG_INTEGER -> valueStack.push(mapper.readValue(p, BigInteger.class));
      case BYTE_STRING -> valueStack.push(mapper.readValue(p, ByteBuffer.class));
      case DATE_TIME -> valueStack.push(mapper.readValue(p, OffsetDateTime.class));
      case INTERVAL -> valueStack.push(mapper.readValue(p, Integer.class));
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
  protected byte verifyType(TtlvObject obj, TtlvMapper mapper,
                            AdjustmentValue.AdjustmentValueBuilder builder) {
    byte type = obj.getType();
    EncodingType encodingType = EncodingType
        .fromTypeValue(type)
        .orElseThrow(
            () -> new IllegalArgumentException("Unknown encoding type: " + HexFormat
                .of()
                .toHexDigits(type))
        );

    encodingTypeStack.push(encodingType);
    builder.encodingType(encodingType);
    return obj.getType();
  }
}
