package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.RngMode;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("RngMode JSON Serialization")
class RngModeJsonTest extends AbstractJsonSerializationSuite<RngMode> {
    @Override
    protected Class<RngMode> type() {
        return RngMode.class;
    }

    @Override
    protected RngMode createDefault() {
        return new RngMode(RngMode.Standard.UNSPECIFIED);
    }

    @Override
    protected RngMode createVariant() {
        return new RngMode(RngMode.Standard.SHARED_INSTANTIATION);
    }
}
