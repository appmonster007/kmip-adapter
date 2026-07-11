package org.purpleBean.kmip.codec.xml.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.ProfileVersionMinor;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProfileVersionMinor Xml Serialization Tests")
class ProfileVersionMinorXmlTest extends AbstractXmlSerializationTestSuite<ProfileVersionMinor> {

    @Override
    public Class<ProfileVersionMinor> type() {
        return ProfileVersionMinor.class;
    }

    @Override
    public ProfileVersionMinor createDefault() {
        return ProfileVersionMinor.of(123);
    }

    @Override
    public ProfileVersionMinor createVariant() {
        return ProfileVersionMinor.of(456);
    }
}