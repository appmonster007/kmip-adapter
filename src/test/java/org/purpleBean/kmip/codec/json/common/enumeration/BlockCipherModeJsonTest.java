package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.BlockCipherMode;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("BlockCipherMode JSON Serialization")
class BlockCipherModeJsonTest extends AbstractJsonSerializationSuite<BlockCipherMode> {
    @Override
    protected Class<BlockCipherMode> type() {
        return BlockCipherMode.class;
    }

    @Override
    protected BlockCipherMode createDefault() {
        return new BlockCipherMode(BlockCipherMode.Standard.CBC);
    }

    @Override
    protected BlockCipherMode createVariant() {
        return new BlockCipherMode(BlockCipherMode.Standard.ECB);
    }
}
