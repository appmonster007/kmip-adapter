package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.LinkType;
import org.purplebean.kmip.model.core.structure.Link;
import org.purplebean.kmip.model.core.type.LinkedObjectIdentifier;

/**
 * TTLV deserializer for {@link Link}.
 */
public class LinkTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Link, Link.LinkBuilder> {

  /**
   * Constructs a new {@link LinkTtlvDeserializer}.
   */
  public LinkTtlvDeserializer() {
    super(Link.kmipTag, Link.encodingType);
  }

  @Override
  protected Link.LinkBuilder createBuilder() {
    return Link.builder();
  }

  @Override
  protected void setValue(Link.LinkBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.LINK_TYPE -> builder.linkType(mapper.readValue(p, LinkType.class));
      case KmipTag.Standard.LINKED_OBJECT_IDENTIFIER ->
          builder.linkedObjectIdentifier(mapper.readValue(p, LinkedObjectIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Link build(Link.LinkBuilder builder) {
    return builder.build();
  }
}