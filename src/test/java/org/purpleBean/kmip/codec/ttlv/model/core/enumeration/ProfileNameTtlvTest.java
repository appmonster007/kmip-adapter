package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ProfileName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ProfileName TTLV Serialization")
class ProfileNameTtlvTest extends AbstractTtlvSerializationTestSuite<ProfileName> {
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
