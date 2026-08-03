package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3x0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.ChildLink;

public class ChildLinkTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<ChildLink, ChildLink.ChildLinkBuilder> {

  public ChildLinkTtlvDeserializer() {
    super(ChildLink.kmipTag, ChildLink.encodingType);
  }

  @Override
  protected ChildLink.ChildLinkBuilder createBuilder() {
    return ChildLink.builder();
  }

  @Override
  protected void setValue(ChildLink.ChildLinkBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ChildLink build(ChildLink.ChildLinkBuilder builder) {
    return builder.build();
  }
}
