package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.enumeration.AsynchronousIndicator;

/**
 * TTLV deserializer for {@link AsynchronousIndicator}.
 */
public class AsynchronousIndicatorTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AsynchronousIndicator,
        AsynchronousIndicator.AsynchronousIndicatorBuilder> {

  /**
   * Constructs a new {@link AsynchronousIndicatorTtlvDeserializer}.
   */
  public AsynchronousIndicatorTtlvDeserializer() {
    super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType);
  }

  @Override
  protected AsynchronousIndicator.AsynchronousIndicatorBuilder createBuilder() {
    return AsynchronousIndicator.builder();
  }

  @Override
  protected void setValue(AsynchronousIndicator.AsynchronousIndicatorBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(AsynchronousIndicator.fromValue(mapper.readValue(p, Integer.class)));
  }

  @Override
  protected AsynchronousIndicator build(
      AsynchronousIndicator.AsynchronousIndicatorBuilder builder) {
    return builder.build();
  }
}