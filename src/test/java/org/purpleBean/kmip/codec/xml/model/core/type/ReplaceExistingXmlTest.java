package org.purpleBean.kmip.codec.xml.model.core.type;

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
import org.purpleBean.kmip.model.core.type.ReplaceExisting;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ReplaceExisting Xml Serialization Tests")
class ReplaceExistingXmlTest extends AbstractXmlSerializationTestSuite<ReplaceExisting> {

    @Override
    public Class<ReplaceExisting> type() {
        return ReplaceExisting.class;
    }

    @Override
    public ReplaceExisting createDefault() {
        return ReplaceExisting.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    public ReplaceExisting createVariant() {
        return ReplaceExisting.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));
    }
}