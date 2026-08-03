package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;

/**
 * TTLV deserializer for {@link ValidityIndicator}.
 */
public class ValidityIndicatorTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidityIndicator,
        ValidityIndicator.ValidityIndicatorBuilder> {

  /**
   * Constructs a new {@link ValidityIndicatorTtlvDeserializer}.
   */
  public ValidityIndicatorTtlvDeserializer() {
    super(ValidityIndicator.kmipTag, ValidityIndicator.encodingType);
  }

  @Override
  protected ValidityIndicator.ValidityIndicatorBuilder createBuilder() {
    return ValidityIndicator.builder();
  }

  @Override
  protected void setValue(ValidityIndicator.ValidityIndicatorBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(ValidityIndicator.fromValue(value));
  }

  @Override
  protected ValidityIndicator build(ValidityIndicator.ValidityIndicatorBuilder builder) {
    return builder.build();
  }
}
