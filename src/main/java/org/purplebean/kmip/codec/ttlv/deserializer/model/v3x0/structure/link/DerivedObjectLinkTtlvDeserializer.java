package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure.link;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.DerivedObjectLink;

/**
 * TTLV deserializer for {@link DerivedObjectLink}.
 */
public class DerivedObjectLinkTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DerivedObjectLink,
        DerivedObjectLink.DerivedObjectLinkBuilder> {

  /**
   * Constructs a new {@link DerivedObjectLinkTtlvDeserializer}.
   */
  public DerivedObjectLinkTtlvDeserializer() {
    super(DerivedObjectLink.kmipTag, DerivedObjectLink.encodingType);
  }

  @Override
  protected DerivedObjectLink.DerivedObjectLinkBuilder createBuilder() {
    return DerivedObjectLink.builder();
  }

  @Override
  protected void setValue(DerivedObjectLink.DerivedObjectLinkBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DerivedObjectLink build(DerivedObjectLink.DerivedObjectLinkBuilder builder) {
    return builder.build();
  }
}
