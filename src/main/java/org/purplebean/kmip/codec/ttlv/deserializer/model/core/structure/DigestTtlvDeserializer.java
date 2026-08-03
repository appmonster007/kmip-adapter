package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.Digest;
import org.purplebean.kmip.model.core.type.DigestValue;

/**
 * TTLV deserializer for {@link Digest}.
 */
public class DigestTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Digest, Digest.DigestBuilder> {

  /**
   * Constructs a new {@link DigestTtlvDeserializer}.
   */
  public DigestTtlvDeserializer() {
    super(Digest.kmipTag, Digest.encodingType);
  }

  @Override
  protected Digest.DigestBuilder createBuilder() {
    return Digest.builder();
  }

  @Override
  protected void setValue(Digest.DigestBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.HASHING_ALGORITHM ->
          builder.hashingAlgorithm(mapper.readValue(p, HashingAlgorithm.class));
      case KmipTag.Standard.DIGEST_VALUE ->
          builder.digestValue(mapper.readValue(p, DigestValue.class));
      case KmipTag.Standard.KEY_FORMAT_TYPE ->
          builder.keyFormatType(mapper.readValue(p, KeyFormatType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Digest build(Digest.DigestBuilder builder) {
    return builder.build();
  }
}