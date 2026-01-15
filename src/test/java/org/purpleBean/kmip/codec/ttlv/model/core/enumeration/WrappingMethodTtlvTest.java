package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("WrappingMethod TTLV Serialization")
class WrappingMethodTtlvTest extends AbstractTtlvSerializationTestSuite<WrappingMethod> {
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
