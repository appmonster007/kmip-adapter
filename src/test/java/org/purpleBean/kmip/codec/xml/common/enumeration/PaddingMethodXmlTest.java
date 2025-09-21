package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.PaddingMethod;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("PaddingMethod XML Serialization")
class PaddingMethodXmlTest extends AbstractXmlSerializationSuite<PaddingMethod> {
    @Override
    protected Class<PaddingMethod> type() {
        return PaddingMethod.class;
    }

    @Override
    protected PaddingMethod createDefault() {
        return new PaddingMethod(PaddingMethod.Standard.NONE);
    }

    @Override
    protected PaddingMethod createVariant() {
        return new PaddingMethod(PaddingMethod.Standard.PKCS5);
    }
}
