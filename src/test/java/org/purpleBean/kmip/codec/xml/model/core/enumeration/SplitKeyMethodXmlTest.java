package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SplitKeyMethod XML Serialization")
class SplitKeyMethodXmlTest extends AbstractXmlSerializationTestSuite<SplitKeyMethod> {
    @Override
    public Class<SplitKeyMethod> type() {
        return SplitKeyMethod.class;
    }

    @Override
    public SplitKeyMethod createDefault() {
        return SplitKeyMethod.Standard.XOR.inst();
    }

    @Override
    public SplitKeyMethod createVariant() {
        return SplitKeyMethod.Standard.POLYNOMIAL_SHARING_GF_216.inst();
    }
}
