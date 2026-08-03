package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.ReplacedObjectLink;

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
