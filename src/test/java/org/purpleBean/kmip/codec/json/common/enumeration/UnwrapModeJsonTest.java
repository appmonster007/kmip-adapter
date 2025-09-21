package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.UnwrapMode;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("UnwrapMode JSON Serialization")
class UnwrapModeJsonTest extends AbstractJsonSerializationSuite<UnwrapMode> {
    @Override
    protected Class<UnwrapMode> type() {
        return UnwrapMode.class;
    }

    @Override
    protected UnwrapMode createDefault() {
        return new UnwrapMode(UnwrapMode.Standard.UNSPECIFIED);
    }

    @Override
    protected UnwrapMode createVariant() {
        return new UnwrapMode(UnwrapMode.Standard.PROCESSED);
    }
}
