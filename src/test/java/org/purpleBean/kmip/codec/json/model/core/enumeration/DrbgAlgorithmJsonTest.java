package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.DrbgAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DrbgAlgorithm JSON Serialization")
class DrbgAlgorithmJsonTest extends AbstractJsonSerializationTestSuite<DrbgAlgorithm> {
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
