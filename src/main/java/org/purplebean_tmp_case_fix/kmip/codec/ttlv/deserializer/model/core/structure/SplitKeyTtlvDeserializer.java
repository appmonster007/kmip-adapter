package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.SplitKey;
import org.purplebean.kmip.model.core.type.KeyPartIdentifier;
import org.purplebean.kmip.model.core.type.PrimeFieldSize;
import org.purplebean.kmip.model.core.type.SplitKeyParts;
import org.purplebean.kmip.model.core.type.SplitKeyThreshold;

public class SplitKeyTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<SplitKey, SplitKey.SplitKeyBuilder> {

  public SplitKeyTtlvDeserializer() {
    super(SplitKey.kmipTag, SplitKey.encodingType);
  }

  @Override
  protected SplitKey.SplitKeyBuilder createBuilder() {
    return SplitKey.builder();
  }

  @Override
  protected void setValue(SplitKey.SplitKeyBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.SPLIT_KEY_PARTS ->
          builder.splitKeyParts(mapper.readValue(p, SplitKeyParts.class));
      case KmipTag.Standard.KEY_PART_IDENTIFIER ->
          builder.keyPartIdentifier(mapper.readValue(p, KeyPartIdentifier.class));
      case KmipTag.Standard.SPLIT_KEY_THRESHOLD ->
          builder.splitKeyThreshold(mapper.readValue(p, SplitKeyThreshold.class));
      case KmipTag.Standard.SPLIT_KEY_METHOD ->
          builder.splitKeyMethod(mapper.readValue(p, SplitKeyMethod.class));
      case KmipTag.Standard.PRIME_FIELD_SIZE ->
          builder.primeFieldSize(mapper.readValue(p, PrimeFieldSize.class));
      case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(mapper.readValue(p, KeyBlock.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SplitKey build(SplitKey.SplitKeyBuilder builder) {
    return builder.build();
  }
}