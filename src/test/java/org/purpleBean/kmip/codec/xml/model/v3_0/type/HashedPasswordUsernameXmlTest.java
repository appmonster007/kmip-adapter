package org.purpleBean.kmip.codec.xml.model.v3_0.type;

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
import org.purpleBean.kmip.model.v3_0.type.HashedPasswordUsername;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("HashedPasswordUsername Xml Serialization Tests")
class HashedPasswordUsernameXmlTest extends AbstractXmlSerializationTestSuite<HashedPasswordUsername> {

    @Override
    public Class<HashedPasswordUsername> type() {
        return HashedPasswordUsername.class;
    }

    @Override
    public HashedPasswordUsername createDefault() {
        return HashedPasswordUsername.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    public HashedPasswordUsername createVariant() {
        return HashedPasswordUsername.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));
    }
}