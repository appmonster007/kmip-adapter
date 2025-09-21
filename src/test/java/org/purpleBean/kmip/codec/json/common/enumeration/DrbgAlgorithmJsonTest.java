package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("DrbgAlgorithm JSON Serialization")
class DrbgAlgorithmJsonTest extends AbstractJsonSerializationSuite<DrbgAlgorithm> {
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
