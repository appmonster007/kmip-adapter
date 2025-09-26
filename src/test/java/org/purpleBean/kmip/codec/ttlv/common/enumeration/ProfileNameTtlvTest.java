package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ProfileName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("ProfileName TTLV Serialization")
class ProfileNameTtlvTest extends AbstractTtlvSerializationSuite<ProfileName> {
    @Override
    protected Class<ProfileName> type() {
        return ProfileName.class;
    }

    @Override
    protected ProfileName createDefault() {
        return new ProfileName(ProfileName.Standard.COMPLETE_SERVER_BASIC);
    }

    @Override
    protected ProfileName createVariant() {
        return new ProfileName(ProfileName.Standard.COMPLETE_SERVER_TLS_V1_2);
    }
}
