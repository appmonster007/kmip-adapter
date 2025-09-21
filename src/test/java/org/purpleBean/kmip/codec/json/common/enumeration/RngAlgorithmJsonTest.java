package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.RngAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("RngAlgorithm JSON Serialization")
class RngAlgorithmJsonTest extends AbstractJsonSerializationSuite<RngAlgorithm> {
    @Override
    protected Class<RngAlgorithm> type() {
        return RngAlgorithm.class;
    }

    @Override
    protected RngAlgorithm createDefault() {
        return new RngAlgorithm(RngAlgorithm.Standard.UNSPECIFIED);
    }

    @Override
    protected RngAlgorithm createVariant() {
        return new RngAlgorithm(RngAlgorithm.Standard.FIPS_186_2);
    }
}
