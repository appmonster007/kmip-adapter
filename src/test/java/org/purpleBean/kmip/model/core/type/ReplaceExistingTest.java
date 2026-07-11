package org.purpleBean.kmip.model.core.type;

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
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("ReplaceExisting Domain Tests")
class ReplaceExistingTest extends AbstractKmipDataTypeTestSuite<ReplaceExisting> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<ReplaceExisting> type() {
        return ReplaceExisting.class;
    }

    @Override
    protected ReplaceExisting createDefault() {
        return ReplaceExisting.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}