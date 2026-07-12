package org.purpleBean.kmip.codec.json.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.RotateDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RotateDate Json Serialization Tests")
class RotateDateJsonTest extends AbstractJsonSerializationTestSuite<RotateDate> {

    @Override
    public Class<RotateDate> type() {
        return RotateDate.class;
    }

    @Override
    public RotateDate createDefault() {
        return RotateDate.of(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC));
    }

    @Override
    public RotateDate createVariant() {
        return RotateDate.of(OffsetDateTime.of(2025, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC));
    }
}