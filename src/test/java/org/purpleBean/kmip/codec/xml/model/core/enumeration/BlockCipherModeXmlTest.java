package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.BlockCipherMode;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("BlockCipherMode XML Serialization")
class BlockCipherModeXmlTest extends AbstractXmlSerializationTestSuite<BlockCipherMode> {
    @Override
    protected Class<BlockCipherMode> type() {
        return BlockCipherMode.class;
    }

    @Override
    protected BlockCipherMode createDefault() {
        return BlockCipherMode.Standard.CBC.inst();
    }

    @Override
    protected BlockCipherMode createVariant() {
        return BlockCipherMode.Standard.ECB.inst();
    }
}
