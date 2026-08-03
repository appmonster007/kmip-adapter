package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.IterationCount;

/**
 * TTLV deserializer for {@link IterationCount}.
 */
public class IterationCountTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<IterationCount, IterationCount.IterationCountBuilder> {

  /**
   * Constructs a new {@link IterationCountTtlvDeserializer}.
   */
  public IterationCountTtlvDeserializer() {
    super(IterationCount.kmipTag, IterationCount.encodingType);
  }

  @Override
  protected IterationCount.IterationCountBuilder createBuilder() {
    return IterationCount.builder();
  }

  @Override
  protected void setValue(IterationCount.IterationCountBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected IterationCount build(IterationCount.IterationCountBuilder builder) {
    return builder.build();
  }
}
