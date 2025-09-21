package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.UnwrapMode;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("UnwrapMode XML Serialization")
class UnwrapModeXmlTest extends AbstractXmlSerializationSuite<UnwrapMode> {
    @Override
    protected Class<UnwrapMode> type() {
        return UnwrapMode.class;
    }

    @Override
    protected UnwrapMode createDefault() {
        return new UnwrapMode(UnwrapMode.Standard.UNSPECIFIED);
    }

    @Override
    protected UnwrapMode createVariant() {
        return new UnwrapMode(UnwrapMode.Standard.PROCESSED);
    }
}
