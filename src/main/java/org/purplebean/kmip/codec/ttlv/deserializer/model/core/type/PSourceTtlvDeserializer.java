package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.PSource;

/**
 * TTLV deserializer for {@link PSource}.
 */
public class PSourceTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<PSource, PSource.PSourceBuilder> {

  /**
   * Constructs a new {@link PSourceTtlvDeserializer}.
   */
  public PSourceTtlvDeserializer() {
    super(PSource.kmipTag, PSource.encodingType);
  }

  @Override
  protected PSource.PSourceBuilder createBuilder() {
    return PSource.builder();
  }

  @Override
  protected void setValue(PSource.PSourceBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, ByteBuffer.class));
  }

  @Override
  protected PSource build(PSource.PSourceBuilder builder) {
    return builder.build();
  }
}
