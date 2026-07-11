package org.purpleBean.kmip.codec.ttlv.model.core.type;

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
import org.purpleBean.kmip.model.core.type.RotateOffset;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RotateOffset Ttlv Serialization Tests")
class RotateOffsetTtlvTest extends AbstractTtlvSerializationTestSuite<RotateOffset> {

    @Override
    public Class<RotateOffset> type() {
        return RotateOffset.class;
    }

    @Override
    public RotateOffset createDefault() {
        return RotateOffset.of(12345L);
    }

    @Override
    public RotateOffset createVariant() {
        return RotateOffset.of(54321L);
    }
}