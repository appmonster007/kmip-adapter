package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("WrappingMethod XML Serialization")
class WrappingMethodXmlTest extends AbstractXmlSerializationSuite<WrappingMethod> {
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
