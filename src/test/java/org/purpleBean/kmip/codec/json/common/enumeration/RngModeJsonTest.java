package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.RngMode;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RngMode JSON Serialization")
class RngModeJsonTest extends AbstractJsonSerializationTestSuite<RngMode> {
    @Override
    protected Class<RngMode> type() {
        return RngMode.class;
    }

    @Override
    protected RngMode createDefault() {
        return RngMode.Standard.UNSPECIFIED.inst();
    }

    @Override
    protected RngMode createVariant() {
        return RngMode.Standard.SHARED_INSTANTIATION.inst();
    }
}
