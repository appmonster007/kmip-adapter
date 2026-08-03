package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.SplitKeyMethod;

/**
 * TTLV deserializer for {@link SplitKeyMethod}.
 */
public class SplitKeyMethodTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SplitKeyMethod, SplitKeyMethod.SplitKeyMethodBuilder> {

  /**
   * Constructs a new {@link SplitKeyMethodTtlvDeserializer}.
   */
  public SplitKeyMethodTtlvDeserializer() {
    super(SplitKeyMethod.kmipTag, SplitKeyMethod.encodingType);
  }

  @Override
  protected SplitKeyMethod.SplitKeyMethodBuilder createBuilder() {
    return SplitKeyMethod.builder();
  }

  @Override
  protected void setValue(SplitKeyMethod.SplitKeyMethodBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(SplitKeyMethod.fromValue(value));
  }

  @Override
  protected SplitKeyMethod build(SplitKeyMethod.SplitKeyMethodBuilder builder) {
    return builder.build();
  }
}
