package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.InitializationVector;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("InitializationVector TTLV Serialization Tests")
class InitializationVectorTtlvTest extends AbstractTtlvSerializationTestSuite<InitializationVector> {

    @Override
    protected Class<InitializationVector> type() {
        return InitializationVector.class;
    }

    @Override
    protected InitializationVector createDefault() {
        return InitializationVector.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected InitializationVector createVariant() {
        return InitializationVector.of(new byte[]{0x04, 0x05, 0x06});
    }
}