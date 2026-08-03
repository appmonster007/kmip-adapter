package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.PgpKey;
import org.purplebean.kmip.model.core.type.PgpKeyVersion;

/**
 * TTLV deserializer for {@link PgpKey}.
 */
public class PgpKeyTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<PgpKey, PgpKey.PgpKeyBuilder> {

  /**
   * Constructs a new {@link PgpKeyTtlvDeserializer}.
   */
  public PgpKeyTtlvDeserializer() {
    super(PgpKey.kmipTag, PgpKey.encodingType);
  }

  @Override
  protected PgpKey.PgpKeyBuilder createBuilder() {
    return PgpKey.builder();
  }

  @Override
  protected void setValue(PgpKey.PgpKeyBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PGP_KEY_VERSION ->
          builder.pgpKeyVersion(mapper.readValue(p, PgpKeyVersion.class));
      case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(mapper.readValue(p, KeyBlock.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected PgpKey build(PgpKey.PgpKeyBuilder builder) {
    return builder.build();
  }
}