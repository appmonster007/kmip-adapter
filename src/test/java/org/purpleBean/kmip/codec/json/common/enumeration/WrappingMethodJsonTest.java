package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("WrappingMethod JSON Serialization")
class WrappingMethodJsonTest extends AbstractJsonSerializationSuite<WrappingMethod> {
    @Override
    protected Class<WrappingMethod> type() {
        return WrappingMethod.class;
    }

    @Override
    protected WrappingMethod createDefault() {
        return new WrappingMethod(WrappingMethod.Standard.ENCRYPT);
    }

    @Override
    protected WrappingMethod createVariant() {
        return new WrappingMethod(WrappingMethod.Standard.MAC_SIGN);
    }
}
