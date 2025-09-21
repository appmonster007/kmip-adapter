package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("SplitKeyMethod TTLV Serialization")
class SplitKeyMethodTtlvTest extends AbstractTtlvSerializationSuite<SplitKeyMethod> {
    @Override
    protected Class<SplitKeyMethod> type() {
        return SplitKeyMethod.class;
    }

    @Override
    protected SplitKeyMethod createDefault() {
        return new SplitKeyMethod(SplitKeyMethod.Standard.XOR);
    }

    @Override
    protected SplitKeyMethod createVariant() {
        return new SplitKeyMethod(SplitKeyMethod.Standard.POLYNOMIAL_SHARING_GF_216);
    }
}
