package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.Ephemeral;

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
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected Ephemeral build(Ephemeral.EphemeralBuilder builder) {
    return builder.build();
  }
}