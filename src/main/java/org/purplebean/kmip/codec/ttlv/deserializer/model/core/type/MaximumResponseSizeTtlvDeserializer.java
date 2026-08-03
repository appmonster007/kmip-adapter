package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.MaximumResponseSize;

/**
 * TTLV deserializer for {@link MaximumResponseSize}.
 */
public class MaximumResponseSizeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<MaximumResponseSize,
        MaximumResponseSize.MaximumResponseSizeBuilder> {

  /**
   * Constructs a new {@link MaximumResponseSizeTtlvDeserializer}.
   */
  public MaximumResponseSizeTtlvDeserializer() {
    super(MaximumResponseSize.kmipTag, MaximumResponseSize.encodingType);
  }

  @Override
  protected MaximumResponseSize.MaximumResponseSizeBuilder createBuilder() {
    return MaximumResponseSize.builder();
  }

  @Override
  protected void setValue(MaximumResponseSize.MaximumResponseSizeBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected MaximumResponseSize build(MaximumResponseSize.MaximumResponseSizeBuilder builder) {
    return builder.build();
  }
}
