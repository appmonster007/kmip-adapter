package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.PrivateKeyLink;

/**
 * TTLV deserializer for {@link PrivateKeyLink}.
 */
public class PrivateKeyLinkTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PrivateKeyLink, PrivateKeyLink.PrivateKeyLinkBuilder> {

  /**
   * Constructs a new {@link PrivateKeyLinkTtlvDeserializer}.
   */
  public PrivateKeyLinkTtlvDeserializer() {
    super(PrivateKeyLink.kmipTag, PrivateKeyLink.encodingType);
  }

  @Override
  protected PrivateKeyLink.PrivateKeyLinkBuilder createBuilder() {
    return PrivateKeyLink.builder();
  }

  @Override
  protected void setValue(PrivateKeyLink.PrivateKeyLinkBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected PrivateKeyLink build(PrivateKeyLink.PrivateKeyLinkBuilder builder) {
    return builder.build();
  }
}
