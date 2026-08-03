package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.ReplacedObjectLink;

public class ReplacedObjectLinkTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ReplacedObjectLink,
        ReplacedObjectLink.ReplacedObjectLinkBuilder> {

  public ReplacedObjectLinkTtlvDeserializer() {
    super(ReplacedObjectLink.kmipTag, ReplacedObjectLink.encodingType);
  }

  @Override
  protected ReplacedObjectLink.ReplacedObjectLinkBuilder createBuilder() {
    return ReplacedObjectLink.builder();
  }

  @Override
  protected void setValue(ReplacedObjectLink.ReplacedObjectLinkBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ReplacedObjectLink build(ReplacedObjectLink.ReplacedObjectLinkBuilder builder) {
    return builder.build();
  }
}
