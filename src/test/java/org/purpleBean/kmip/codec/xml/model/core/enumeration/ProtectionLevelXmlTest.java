package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ProtectionLevel;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProtectionLevel XML Serialization")
class ProtectionLevelXmlTest extends AbstractXmlSerializationTestSuite<ProtectionLevel> {
    @Override
    protected Class<ProtectionLevel> type() {
        return ProtectionLevel.class;
    }

    @Override
    protected ProtectionLevel createDefault() {
        return ProtectionLevel.Standard.HIGH.inst();
    }

    @Override
    protected ProtectionLevel createVariant() {
        return ProtectionLevel.Standard.LOW.inst();
    }
}
