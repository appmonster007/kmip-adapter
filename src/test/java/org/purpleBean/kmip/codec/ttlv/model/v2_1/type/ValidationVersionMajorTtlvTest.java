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
import org.purpleBean.kmip.model.v2_1.type.ValidationVersionMajor;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidationVersionMajor Ttlv Serialization Tests")
class ValidationVersionMajorTtlvTest extends AbstractTtlvSerializationTestSuite<ValidationVersionMajor> {

    @Override
    public Class<ValidationVersionMajor> type() {
        return ValidationVersionMajor.class;
    }

    @Override
    public ValidationVersionMajor createDefault() {
        return ValidationVersionMajor.of(123);
    }

    @Override
    public ValidationVersionMajor createVariant() {
        return ValidationVersionMajor.of(456);
    }
}