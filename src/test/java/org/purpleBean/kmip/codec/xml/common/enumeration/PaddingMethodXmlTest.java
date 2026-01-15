package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.PaddingMethod;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PaddingMethod XML Serialization")
class PaddingMethodXmlTest extends AbstractXmlSerializationTestSuite<PaddingMethod> {
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
