package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3x0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.PublicKeyLink;

public class PublicKeyLinkTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PublicKeyLink, PublicKeyLink.PublicKeyLinkBuilder> {

  public PublicKeyLinkTtlvDeserializer() {
    super(PublicKeyLink.kmipTag, PublicKeyLink.encodingType);
  }

  @Override
  protected PublicKeyLink.PublicKeyLinkBuilder createBuilder() {
    return PublicKeyLink.builder();
  }

  @Override
  protected void setValue(PublicKeyLink.PublicKeyLinkBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected PublicKeyLink build(PublicKeyLink.PublicKeyLinkBuilder builder) {
    return builder.build();
  }
}
