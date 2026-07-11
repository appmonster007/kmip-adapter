package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.type.ProfileVersionMajor;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ProfileVersionMajor Ttlv Serialization Tests")
class ProfileVersionMajorTtlvTest extends AbstractTtlvSerializationTestSuite<ProfileVersionMajor> {

    @Override
    public Class<ProfileVersionMajor> type() {
        return ProfileVersionMajor.class;
    }

    @Override
    public ProfileVersionMajor createDefault() {
        return ProfileVersionMajor.of(123);
    }

    @Override
    public ProfileVersionMajor createVariant() {
        return ProfileVersionMajor.of(456);
    }
}