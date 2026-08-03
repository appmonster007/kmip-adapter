package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KeyValue;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.enumeration.KeyCompressionType;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.KeyWrappingData;
import org.purplebean.kmip.model.core.type.CryptographicLength;

/**
 * TTLV deserializer for {@link KeyBlock}.
 */
public class KeyBlockTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<KeyBlock, KeyBlock.KeyBlockBuilder> {

  /**
   * Constructs a new {@link KeyBlockTtlvDeserializer}.
   */
  public KeyBlockTtlvDeserializer() {
    super(KeyBlock.kmipTag, KeyBlock.encodingType);
  }

  @Override
  protected KeyBlock.KeyBlockBuilder createBuilder() {
    return KeyBlock.builder();
  }

  @Override
  protected void setValue(KeyBlock.KeyBlockBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.KEY_FORMAT_TYPE -> {
        KeyFormatType keyFormatType = mapper.readValue(p, KeyFormatType.class);
        builder.keyFormatType(keyFormatType);
        mapper.setAttribute("keyFormatType", keyFormatType.getDescription());
      }
      case KmipTag.Standard.KEY_COMPRESSION_TYPE ->
          builder.keyCompressionType(mapper.readValue(p, KeyCompressionType.class));
      case KmipTag.Standard.KEY_VALUE ->
          builder.keyValue((KeyValue) mapper.readValue(p, KmipDataType.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_ALGORITHM ->
          builder.cryptographicAlgorithm(mapper.readValue(p, CryptographicAlgorithm.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_LENGTH ->
          builder.cryptographicLength(mapper.readValue(p, CryptographicLength.class));
      case KmipTag.Standard.KEY_WRAPPING_DATA ->
          builder.keyWrappingData(mapper.readValue(p, KeyWrappingData.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected KeyBlock build(KeyBlock.KeyBlockBuilder builder) {
    return builder.build();
  }
}