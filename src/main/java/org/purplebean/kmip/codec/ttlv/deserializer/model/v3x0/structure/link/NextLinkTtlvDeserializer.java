package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.structure.link.NextLink;

/**
 * TTLV deserializer for {@link NextLink}.
 */
public class NextLinkTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<NextLink, NextLink.NextLinkBuilder> {

  /**
   * Constructs a new {@link NextLinkTtlvDeserializer}.
   */
  public NextLinkTtlvDeserializer() {
    super(NextLink.kmipTag, NextLink.encodingType);
  }

  @Override
  protected NextLink.NextLinkBuilder createBuilder() {
    return NextLink.builder();
  }

  @Override
  protected void setValue(NextLink.NextLinkBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected NextLink build(NextLink.NextLinkBuilder builder) {
    return builder.build();
  }
}
