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
import org.purpleBean.kmip.model.core.type.RotateLatest;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RotateLatest Ttlv Serialization Tests")
class RotateLatestTtlvTest extends AbstractTtlvSerializationTestSuite<RotateLatest> {

    @Override
    public Class<RotateLatest> type() {
        return RotateLatest.class;
    }

    @Override
    public RotateLatest createDefault() {
        return RotateLatest.of(true);
    }

    @Override
    public RotateLatest createVariant() {
        return RotateLatest.of(false);
    }
}