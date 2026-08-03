package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.BlockCipherMode;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("BlockCipherMode XML Serialization")
class BlockCipherModeXmlTest extends AbstractXmlSerializationTestSuite<BlockCipherMode> {
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
