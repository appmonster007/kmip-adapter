package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.WrappingMethod;

/**
 * TTLV deserializer for {@link WrappingMethod}.
 */
public class WrappingMethodTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<WrappingMethod, WrappingMethod.WrappingMethodBuilder> {

  /**
   * Constructs a new {@link WrappingMethodTtlvDeserializer}.
   */
  public WrappingMethodTtlvDeserializer() {
    super(WrappingMethod.kmipTag, WrappingMethod.encodingType);
  }

  @Override
  protected WrappingMethod.WrappingMethodBuilder createBuilder() {
    return WrappingMethod.builder();
  }

  @Override
  protected void setValue(WrappingMethod.WrappingMethodBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(WrappingMethod.fromValue(value));
  }

  @Override
  protected WrappingMethod build(WrappingMethod.WrappingMethodBuilder builder) {
    return builder.build();
  }
}
