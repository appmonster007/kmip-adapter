package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.SecretDataType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.SecretData;

/**
 * TTLV deserializer for {@link SecretData}.
 */
public class SecretDataTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<SecretData, SecretData.SecretDataBuilder> {

  /**
   * Constructs a new {@link SecretDataTtlvDeserializer}.
   */
  public SecretDataTtlvDeserializer() {
    super(SecretData.kmipTag, SecretData.encodingType);
  }

  @Override
  protected SecretData.SecretDataBuilder createBuilder() {
    return SecretData.builder();
  }

  @Override
  protected void setValue(SecretData.SecretDataBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.SECRET_DATA_TYPE ->
          builder.secretDataType(mapper.readValue(p, SecretDataType.class));
      case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(mapper.readValue(p, KeyBlock.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SecretData build(SecretData.SecretDataBuilder builder) {
    return builder.build();
  }
}