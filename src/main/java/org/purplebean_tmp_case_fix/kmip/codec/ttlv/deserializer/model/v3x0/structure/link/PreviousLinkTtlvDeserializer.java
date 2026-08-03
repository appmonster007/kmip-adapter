package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.PreviousLink;

public class PreviousLinkTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<PreviousLink, PreviousLink.PreviousLinkBuilder> {

  public PreviousLinkTtlvDeserializer() {
    super(PreviousLink.kmipTag, PreviousLink.encodingType);
  }

  @Override
  protected PreviousLink.PreviousLinkBuilder createBuilder() {
    return PreviousLink.builder();
  }

  @Override
  protected void setValue(PreviousLink.PreviousLinkBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected PreviousLink build(PreviousLink.PreviousLinkBuilder builder) {
    return builder.build();
  }
}
