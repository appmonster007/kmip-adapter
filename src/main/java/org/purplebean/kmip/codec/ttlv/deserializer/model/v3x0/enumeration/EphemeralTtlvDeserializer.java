package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.enumeration.Ephemeral;

/**
 * TTLV deserializer for {@link Ephemeral}.
 */
public class EphemeralTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Ephemeral, Ephemeral.EphemeralBuilder> {

  /**
   * Constructs a new {@link EphemeralTtlvDeserializer}.
   */
  public EphemeralTtlvDeserializer() {
    super(Ephemeral.kmipTag, Ephemeral.encodingType);
  }

  @Override
  protected Ephemeral.EphemeralBuilder createBuilder() {
    return Ephemeral.builder();
  }

  @Override
  protected void setValue(Ephemeral.EphemeralBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(Ephemeral.fromValue(value));
  }

  @Override
  protected Ephemeral build(Ephemeral.EphemeralBuilder builder) {
    return builder.build();
  }
}