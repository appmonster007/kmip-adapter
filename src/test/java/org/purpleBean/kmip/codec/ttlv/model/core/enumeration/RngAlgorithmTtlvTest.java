package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RngAlgorithm TTLV Serialization")
class RngAlgorithmTtlvTest extends AbstractTtlvSerializationTestSuite<RngAlgorithm> {
    @Override
    protected Class<RngAlgorithm> type() {
        return RngAlgorithm.class;
    }

    @Override
    protected RngAlgorithm createDefault() {
        return RngAlgorithm.Standard.UNSPECIFIED.inst();
    }

    @Override
    protected RngAlgorithm createVariant() {
        return RngAlgorithm.Standard.FIPS_186_2.inst();
    }
}
