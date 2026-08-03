package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.QuantumSafeCapability;

/**
 * TTLV deserializer for {@link QuantumSafeCapability}.
 */
public class QuantumSafeCapabilityTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<QuantumSafeCapability,
        QuantumSafeCapability.QuantumSafeCapabilityBuilder> {

  /**
   * Constructs a new {@link QuantumSafeCapabilityTtlvDeserializer}.
   */
  public QuantumSafeCapabilityTtlvDeserializer() {
    super(QuantumSafeCapability.kmipTag, QuantumSafeCapability.encodingType);
  }

  @Override
  protected QuantumSafeCapability.QuantumSafeCapabilityBuilder createBuilder() {
    return QuantumSafeCapability.builder();
  }

  @Override
  protected void setValue(QuantumSafeCapability.QuantumSafeCapabilityBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected QuantumSafeCapability build(
      QuantumSafeCapability.QuantumSafeCapabilityBuilder builder) {
    return builder.build();
  }
}