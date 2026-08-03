package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3x0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.ParentLink;

public class ParentLinkTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<ParentLink, ParentLink.ParentLinkBuilder> {

  public ParentLinkTtlvDeserializer() {
    super(ParentLink.kmipTag, ParentLink.encodingType);
  }

  @Override
  protected ParentLink.ParentLinkBuilder createBuilder() {
    return ParentLink.builder();
  }

  @Override
  protected void setValue(ParentLink.ParentLinkBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ParentLink build(ParentLink.ParentLinkBuilder builder) {
    return builder.build();
  }
}
