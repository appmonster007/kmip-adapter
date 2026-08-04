package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.structure.link.PreviousLink;

/**
 * TTLV deserializer for {@link PreviousLink}.
 */
public class PreviousLinkTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PreviousLink, PreviousLink.PreviousLinkBuilder> {

  /**
   * Constructs a new {@link PreviousLinkTtlvDeserializer}.
   */
  public PreviousLinkTtlvDeserializer() {
    super(PreviousLink.kmipTag, PreviousLink.encodingType);
  }

  @Override
  protected PreviousLink.PreviousLinkBuilder createBuilder() {
    return PreviousLink.builder();
  }

  @Override
  protected void setValue(PreviousLink.PreviousLinkBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected PreviousLink build(PreviousLink.PreviousLinkBuilder builder) {
    return builder.build();
  }
}
