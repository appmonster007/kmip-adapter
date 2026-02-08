package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CryptographicUsageMask JSON Serialization Tests")
class CryptographicUsageMaskJsonTest extends AbstractJsonSerializationTestSuite<CryptographicUsageMask> {

    @Override
    public Class<CryptographicUsageMask> type() {
        return CryptographicUsageMask.class;
    }

    @Override
    public CryptographicUsageMask createDefault() {
        return CryptographicUsageMask.builder().value(10).build();
    }

    @Override
    public CryptographicUsageMask createVariant() {
        return CryptographicUsageMask.builder().value(100).build();
    }
}
