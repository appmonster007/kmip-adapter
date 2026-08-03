package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.StreamingCapability;

/**
 * TTLV deserializer for {@link StreamingCapability}.
 */
public class StreamingCapabilityTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<StreamingCapability,
        StreamingCapability.StreamingCapabilityBuilder> {

  /**
   * Constructs a new {@link StreamingCapabilityTtlvDeserializer}.
   */
  public StreamingCapabilityTtlvDeserializer() {
    super(StreamingCapability.kmipTag, StreamingCapability.encodingType);
  }

  @Override
  protected StreamingCapability.StreamingCapabilityBuilder createBuilder() {
    return StreamingCapability.builder();
  }

  @Override
  protected void setValue(StreamingCapability.StreamingCapabilityBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected StreamingCapability build(StreamingCapability.StreamingCapabilityBuilder builder) {
    return builder.build();
  }
}