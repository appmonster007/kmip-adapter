package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.BlockCipherMode;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("BlockCipherMode TTLV Serialization")
class BlockCipherModeTtlvTest extends AbstractTtlvSerializationTestSuite<BlockCipherMode> {
  @Override
  public Class<BlockCipherMode> type() {
    return BlockCipherMode.class;
  }

  @Override
  public BlockCipherMode createDefault() {
    return BlockCipherMode.Standard.CBC.inst();
  }

  @Override
  public BlockCipherMode createVariant() {
    return BlockCipherMode.Standard.ECB.inst();
  }
}
