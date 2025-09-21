package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("DrbgAlgorithm TTLV Serialization")
class DrbgAlgorithmTtlvTest extends AbstractTtlvSerializationSuite<DrbgAlgorithm> {
    @Override
    protected Class<DrbgAlgorithm> type() {
        return DrbgAlgorithm.class;
    }

    @Override
    protected DrbgAlgorithm createDefault() {
        return new DrbgAlgorithm(DrbgAlgorithm.Standard.UNSPECIFIED);
    }

    @Override
    protected DrbgAlgorithm createVariant() {
        return new DrbgAlgorithm(DrbgAlgorithm.Standard.DUAL_EC);
    }
}
