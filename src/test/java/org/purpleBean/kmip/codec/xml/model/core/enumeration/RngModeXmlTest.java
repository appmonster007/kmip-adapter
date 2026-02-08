package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RngMode;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RngMode XML Serialization")
class RngModeXmlTest extends AbstractXmlSerializationTestSuite<RngMode> {
    @Override
    public Class<RngMode> type() {
        return RngMode.class;
    }

    @Override
    public RngMode createDefault() {
        return RngMode.Standard.UNSPECIFIED.inst();
    }

    @Override
    public RngMode createVariant() {
        return RngMode.Standard.SHARED_INSTANTIATION.inst();
    }
}
