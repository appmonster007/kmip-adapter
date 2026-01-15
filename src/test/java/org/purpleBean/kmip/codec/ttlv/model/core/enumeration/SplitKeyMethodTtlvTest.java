package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SplitKeyMethod TTLV Serialization")
class SplitKeyMethodTtlvTest extends AbstractTtlvSerializationTestSuite<SplitKeyMethod> {
    @Override
    protected Class<SplitKeyMethod> type() {
        return SplitKeyMethod.class;
    }

    @Override
    protected SplitKeyMethod createDefault() {
        return SplitKeyMethod.Standard.XOR.inst();
    }

    @Override
    protected SplitKeyMethod createVariant() {
        return SplitKeyMethod.Standard.POLYNOMIAL_SHARING_GF_216.inst();
    }
}
