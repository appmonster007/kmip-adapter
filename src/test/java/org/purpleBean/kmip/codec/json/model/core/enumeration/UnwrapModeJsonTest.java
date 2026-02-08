package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.UnwrapMode;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("UnwrapMode JSON Serialization")
class UnwrapModeJsonTest extends AbstractJsonSerializationTestSuite<UnwrapMode> {
    @Override
    public Class<UnwrapMode> type() {
        return UnwrapMode.class;
    }

    @Override
    public UnwrapMode createDefault() {
        return UnwrapMode.Standard.UNSPECIFIED.inst();
    }

    @Override
    public UnwrapMode createVariant() {
        return UnwrapMode.Standard.PROCESSED.inst();
    }
}
