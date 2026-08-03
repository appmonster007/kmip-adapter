package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.DerivationObjectLink;

/**
 * TTLV deserializer for {@link DerivationObjectLink}.
 */
public class DerivationObjectLinkTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DerivationObjectLink,
        DerivationObjectLink.DerivationObjectLinkBuilder> {

  /**
   * Constructs a new {@link DerivationObjectLinkTtlvDeserializer}.
   */
  public DerivationObjectLinkTtlvDeserializer() {
    super(DerivationObjectLink.kmipTag, DerivationObjectLink.encodingType);
  }

  @Override
  protected DerivationObjectLink.DerivationObjectLinkBuilder createBuilder() {
    return DerivationObjectLink.builder();
  }

  @Override
  protected void setValue(DerivationObjectLink.DerivationObjectLinkBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DerivationObjectLink build(DerivationObjectLink.DerivationObjectLinkBuilder builder) {
    return builder.build();
  }
}
