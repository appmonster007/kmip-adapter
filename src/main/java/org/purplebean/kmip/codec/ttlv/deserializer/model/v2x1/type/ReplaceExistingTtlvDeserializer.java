package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.ReplaceExisting;

/**
 * TTLV deserializer for {@link ReplaceExisting}.
 */
public class ReplaceExistingTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ReplaceExisting, ReplaceExisting.ReplaceExistingBuilder> {

  /**
   * Constructs a new {@link ReplaceExistingTtlvDeserializer}.
   */
  public ReplaceExistingTtlvDeserializer() {
    super(ReplaceExisting.kmipTag, ReplaceExisting.encodingType);
  }

  @Override
  protected ReplaceExisting.ReplaceExistingBuilder createBuilder() {
    return ReplaceExisting.builder();
  }

  @Override
  protected void setValue(ReplaceExisting.ReplaceExistingBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected ReplaceExisting build(ReplaceExisting.ReplaceExistingBuilder builder) {
    return builder.build();
  }
}