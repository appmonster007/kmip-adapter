package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.RotateGeneration;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RotateGeneration Ttlv Serialization Tests")
class RotateGenerationTtlvTest extends AbstractTtlvSerializationTestSuite<RotateGeneration> {

    @Override
    public Class<RotateGeneration> type() {
        return RotateGeneration.class;
    }

    @Override
    public RotateGeneration createDefault() {
        return RotateGeneration.of(123);
    }

    @Override
    public RotateGeneration createVariant() {
        return RotateGeneration.of(456);
    }
}