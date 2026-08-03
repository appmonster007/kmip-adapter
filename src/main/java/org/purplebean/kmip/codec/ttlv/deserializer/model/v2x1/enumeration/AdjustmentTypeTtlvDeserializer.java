package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.enumeration.AdjustmentType;

/**
 * TTLV deserializer for {@link AdjustmentType}.
 */
public class AdjustmentTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AdjustmentType, AdjustmentType.AdjustmentTypeBuilder> {

  /**
   * Constructs a new {@link AdjustmentTypeTtlvDeserializer}.
   */
  public AdjustmentTypeTtlvDeserializer() {
    super(AdjustmentType.kmipTag, AdjustmentType.encodingType);
  }

  @Override
  protected AdjustmentType.AdjustmentTypeBuilder createBuilder() {
    return AdjustmentType.builder();
  }

  @Override
  protected void setValue(AdjustmentType.AdjustmentTypeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(AdjustmentType.fromValue(value));
  }

  @Override
  protected AdjustmentType build(AdjustmentType.AdjustmentTypeBuilder builder) {
    return builder.build();
  }
}