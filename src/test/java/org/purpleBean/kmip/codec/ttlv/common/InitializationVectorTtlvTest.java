package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.InitializationVector;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("InitializationVector TTLV Serialization Tests")
class InitializationVectorTtlvTest extends AbstractTtlvSerializationSuite<InitializationVector> {

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