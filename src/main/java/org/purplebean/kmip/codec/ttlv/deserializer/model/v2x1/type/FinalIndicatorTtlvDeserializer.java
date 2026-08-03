package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.FinalIndicator;

/**
 * TTLV deserializer for {@link FinalIndicator}.
 */
public class FinalIndicatorTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<FinalIndicator, FinalIndicator.FinalIndicatorBuilder> {

  /**
   * Constructs a new {@link FinalIndicatorTtlvDeserializer}.
   */
  public FinalIndicatorTtlvDeserializer() {
    super(FinalIndicator.kmipTag, FinalIndicator.encodingType);
  }

  @Override
  protected FinalIndicator.FinalIndicatorBuilder createBuilder() {
    return FinalIndicator.builder();
  }

  @Override
  protected void setValue(FinalIndicator.FinalIndicatorBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected FinalIndicator build(FinalIndicator.FinalIndicatorBuilder builder) {
    return builder.build();
  }
}