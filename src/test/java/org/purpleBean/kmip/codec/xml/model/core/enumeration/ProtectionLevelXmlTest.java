package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ProtectionLevel;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProtectionLevel XML Serialization")
class ProtectionLevelXmlTest extends AbstractXmlSerializationTestSuite<ProtectionLevel> {
    @Override
    public Class<ProtectionLevel> type() {
        return ProtectionLevel.class;
    }

    @Override
    public ProtectionLevel createDefault() {
        return ProtectionLevel.Standard.HIGH.inst();
    }

    @Override
    public ProtectionLevel createVariant() {
        return ProtectionLevel.Standard.LOW.inst();
    }
}
