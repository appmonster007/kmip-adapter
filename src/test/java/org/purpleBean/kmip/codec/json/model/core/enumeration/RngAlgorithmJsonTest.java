package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RngAlgorithm JSON Serialization")
class RngAlgorithmJsonTest extends AbstractJsonSerializationTestSuite<RngAlgorithm> {
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
