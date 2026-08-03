package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.UnwrapMode;

/**
 * TTLV deserializer for {@link UnwrapMode}.
 */
public class UnwrapModeTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<UnwrapMode, UnwrapMode.UnwrapModeBuilder> {

  /**
   * Constructs a new {@link UnwrapModeTtlvDeserializer}.
   */
  public UnwrapModeTtlvDeserializer() {
    super(UnwrapMode.kmipTag, UnwrapMode.encodingType);
  }

  @Override
  protected UnwrapMode.UnwrapModeBuilder createBuilder() {
    return UnwrapMode.builder();
  }

  @Override
  protected void setValue(UnwrapMode.UnwrapModeBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(UnwrapMode.fromValue(value));
  }

  @Override
  protected UnwrapMode build(UnwrapMode.UnwrapModeBuilder builder) {
    return builder.build();
  }
}
