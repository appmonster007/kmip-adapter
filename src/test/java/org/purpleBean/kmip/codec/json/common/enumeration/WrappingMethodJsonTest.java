package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("WrappingMethod JSON Serialization")
class WrappingMethodJsonTest extends AbstractJsonSerializationTestSuite<WrappingMethod> {
    @Override
    protected Class<WrappingMethod> type() {
        return WrappingMethod.class;
    }

    @Override
    protected WrappingMethod createDefault() {
        return WrappingMethod.Standard.ENCRYPT.inst();
    }

    @Override
    protected WrappingMethod createVariant() {
        return WrappingMethod.Standard.MAC_SIGN.inst();
    }
}
