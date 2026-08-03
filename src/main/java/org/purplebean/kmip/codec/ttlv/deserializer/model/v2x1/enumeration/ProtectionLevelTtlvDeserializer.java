package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.enumeration.ProtectionLevel;

/**
 * TTLV deserializer for {@link ProtectionLevel}.
 */
public class ProtectionLevelTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProtectionLevel, ProtectionLevel.ProtectionLevelBuilder> {

  /**
   * Constructs a new {@link ProtectionLevelTtlvDeserializer}.
   */
  public ProtectionLevelTtlvDeserializer() {
    super(ProtectionLevel.kmipTag, ProtectionLevel.encodingType);
  }

  @Override
  protected ProtectionLevel.ProtectionLevelBuilder createBuilder() {
    return ProtectionLevel.builder();
  }

  @Override
  protected void setValue(ProtectionLevel.ProtectionLevelBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(ProtectionLevel.fromValue(value));
  }

  @Override
  protected ProtectionLevel build(ProtectionLevel.ProtectionLevelBuilder builder) {
    return builder.build();
  }
}
