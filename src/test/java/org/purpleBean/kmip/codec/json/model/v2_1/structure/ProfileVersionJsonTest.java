package org.purpleBean.kmip.codec.json.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.structure.ProfileVersion;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ProfileVersion Json Serialization Tests")
class ProfileVersionJsonTest extends AbstractJsonSerializationTestSuite<ProfileVersion> {

    @Override
    public Class<ProfileVersion> type() {
        return ProfileVersion.class;
    }

    @Override
    public ProfileVersion createDefault() {
        return ProfileVersion.builder().build();
    }

    @Override
    public ProfileVersion createVariant() {
        return ProfileVersion.builder().build();
    }
}