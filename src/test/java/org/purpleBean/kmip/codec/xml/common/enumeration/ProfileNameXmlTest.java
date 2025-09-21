package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ProfileName;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("ProfileName XML Serialization")
class ProfileNameXmlTest extends AbstractXmlSerializationSuite<ProfileName> {
    @Override
    protected Class<ProfileName> type() {
        return ProfileName.class;
    }

    @Override
    protected ProfileName createDefault() {
        return new ProfileName(ProfileName.Standard.PLACEHOLDER_1);
    }

    @Override
    protected ProfileName createVariant() {
        return new ProfileName(ProfileName.Standard.PLACEHOLDER_2);
    }
}
