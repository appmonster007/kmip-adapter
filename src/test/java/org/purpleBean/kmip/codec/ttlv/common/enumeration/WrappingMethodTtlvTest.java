package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("WrappingMethod TTLV Serialization")
class WrappingMethodTtlvTest extends AbstractTtlvSerializationSuite<WrappingMethod> {
    @Override
    protected Class<WrappingMethod> type() {
        return WrappingMethod.class;
    }

    @Override
    protected WrappingMethod createDefault() {
        return new WrappingMethod(WrappingMethod.Standard.PLACEHOLDER_1);
    }

    @Override
    protected WrappingMethod createVariant() {
        return new WrappingMethod(WrappingMethod.Standard.PLACEHOLDER_2);
    }
}
