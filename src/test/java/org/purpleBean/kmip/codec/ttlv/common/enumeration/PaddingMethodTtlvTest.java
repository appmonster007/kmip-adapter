package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.PaddingMethod;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PaddingMethod TTLV Serialization")
class PaddingMethodTtlvTest extends AbstractTtlvSerializationTestSuite<PaddingMethod> {
    @Override
    protected Class<PaddingMethod> type() {
        return PaddingMethod.class;
    }

    @Override
    protected PaddingMethod createDefault() {
        return PaddingMethod.Standard.NONE.inst();
    }

    @Override
    protected PaddingMethod createVariant() {
        return PaddingMethod.Standard.PKCS5.inst();
    }
}
