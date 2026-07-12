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
import org.purpleBean.kmip.model.v2_1.type.ShortUniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ShortUniqueIdentifier Xml Serialization Tests")
class ShortUniqueIdentifierXmlTest extends AbstractXmlSerializationTestSuite<ShortUniqueIdentifier> {

    @Override
    public Class<ShortUniqueIdentifier> type() {
        return ShortUniqueIdentifier.class;
    }

    @Override
    public ShortUniqueIdentifier createDefault() {
        return ShortUniqueIdentifier.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    public ShortUniqueIdentifier createVariant() {
        return ShortUniqueIdentifier.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));
    }
}