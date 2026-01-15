package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DrbgAlgorithm TTLV Serialization")
class DrbgAlgorithmTtlvTest extends AbstractTtlvSerializationTestSuite<DrbgAlgorithm> {
    @Override
    protected Class<DrbgAlgorithm> type() {
        return DrbgAlgorithm.class;
    }

    @Override
    protected DrbgAlgorithm createDefault() {
        return DrbgAlgorithm.Standard.UNSPECIFIED.inst();
    }

    @Override
    protected DrbgAlgorithm createVariant() {
        return DrbgAlgorithm.Standard.DUAL_EC.inst();
    }
}
