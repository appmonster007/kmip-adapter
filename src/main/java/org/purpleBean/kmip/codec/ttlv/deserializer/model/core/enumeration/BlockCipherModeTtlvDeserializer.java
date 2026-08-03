package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.BlockCipherMode;

public class BlockCipherModeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<BlockCipherMode, BlockCipherMode.BlockCipherModeBuilder> {

  public BlockCipherModeTtlvDeserializer() {
    super(BlockCipherMode.kmipTag, BlockCipherMode.encodingType);
  }

  @Override
  protected BlockCipherMode.BlockCipherModeBuilder createBuilder() {
    return BlockCipherMode.builder();
  }

  @Override
  protected void setValue(BlockCipherMode.BlockCipherModeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(BlockCipherMode.fromValue(value));
  }

  @Override
  protected BlockCipherMode build(BlockCipherMode.BlockCipherModeBuilder builder) {
    return builder.build();
  }
}
