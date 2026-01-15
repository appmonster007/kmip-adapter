package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.BlockCipherMode;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("BlockCipherMode JSON Serialization")
class BlockCipherModeJsonTest extends AbstractJsonSerializationTestSuite<BlockCipherMode> {
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
