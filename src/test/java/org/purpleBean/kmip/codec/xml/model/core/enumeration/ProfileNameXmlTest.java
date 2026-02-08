package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ProfileName;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProfileName XML Serialization")
class ProfileNameXmlTest extends AbstractXmlSerializationTestSuite<ProfileName> {
    @Override
    public Class<ProfileName> type() {
        return ProfileName.class;
    }

    @Override
    public ProfileName createDefault() {
        return ProfileName.Standard.COMPLETE_SERVER_BASIC.inst();
    }

    @Override
    public ProfileName createVariant() {
        return ProfileName.Standard.COMPLETE_SERVER_TLS_V1_2.inst();
    }
}
