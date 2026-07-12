package org.purpleBean.kmip.codec.xml.model.v2_1.structure;

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
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProfileVersion Xml Serialization Tests")
class ProfileVersionXmlTest extends AbstractXmlSerializationTestSuite<ProfileVersion> {

    @Override
    public Class<ProfileVersion> type() {
        return ProfileVersion.class;
    }

    @Override
    public ProfileVersion createDefault() {
        return ProfileVersion.of(2, 1);
    }

    @Override
    public ProfileVersion createVariant() {
        return ProfileVersion.of(3, 0);
    }
}