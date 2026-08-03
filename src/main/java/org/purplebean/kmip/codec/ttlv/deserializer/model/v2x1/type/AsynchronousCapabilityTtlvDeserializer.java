package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.AsynchronousCapability;

/**
 * TTLV deserializer for {@link AsynchronousCapability}.
 */
public class AsynchronousCapabilityTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AsynchronousCapability,
        AsynchronousCapability.AsynchronousCapabilityBuilder> {

  /**
   * Constructs a new {@link AsynchronousCapabilityTtlvDeserializer}.
   */
  public AsynchronousCapabilityTtlvDeserializer() {
    super(AsynchronousCapability.kmipTag, AsynchronousCapability.encodingType);
  }

  @Override
  protected AsynchronousCapability.AsynchronousCapabilityBuilder createBuilder() {
    return AsynchronousCapability.builder();
  }

  @Override
  protected void setValue(AsynchronousCapability.AsynchronousCapabilityBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected AsynchronousCapability build(
      AsynchronousCapability.AsynchronousCapabilityBuilder builder) {
    return builder.build();
  }
}