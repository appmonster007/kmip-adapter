package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SplitKeyMethod JSON Serialization")
class SplitKeyMethodJsonTest extends AbstractJsonSerializationTestSuite<SplitKeyMethod> {
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
