package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.CriticalityIndicator;

/**
 * TTLV deserializer for {@link CriticalityIndicator}.
 */
public class CriticalityIndicatorTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CriticalityIndicator,
        CriticalityIndicator.CriticalityIndicatorBuilder> {

  /**
   * Constructs a new {@link CriticalityIndicatorTtlvDeserializer}.
   */
  public CriticalityIndicatorTtlvDeserializer() {
    super(CriticalityIndicator.kmipTag, CriticalityIndicator.encodingType);
  }

  @Override
  protected CriticalityIndicator.CriticalityIndicatorBuilder createBuilder() {
    return CriticalityIndicator.builder();
  }

  @Override
  protected void setValue(CriticalityIndicator.CriticalityIndicatorBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Boolean.class));
  }

  @Override
  protected CriticalityIndicator build(CriticalityIndicator.CriticalityIndicatorBuilder builder) {
    return builder.build();
  }
}
