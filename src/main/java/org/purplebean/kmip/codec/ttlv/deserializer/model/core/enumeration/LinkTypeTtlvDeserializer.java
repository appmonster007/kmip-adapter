package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.LinkType;

/**
 * TTLV deserializer for {@link LinkType}.
 */
public class LinkTypeTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<LinkType, LinkType.LinkTypeBuilder> {

  /**
   * Constructs a new {@link LinkTypeTtlvDeserializer}.
   */
  public LinkTypeTtlvDeserializer() {
    super(LinkType.kmipTag, LinkType.encodingType);
  }

  @Override
  protected LinkType.LinkTypeBuilder createBuilder() {
    return LinkType.builder();
  }

  @Override
  protected void setValue(LinkType.LinkTypeBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(LinkType.fromValue(value));
  }

  @Override
  protected LinkType build(LinkType.LinkTypeBuilder builder) {
    return builder.build();
  }
}
