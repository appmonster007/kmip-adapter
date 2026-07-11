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
import org.purpleBean.kmip.model.v2_1.type.SubmissionDate;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SubmissionDate Ttlv Serialization Tests")
class SubmissionDateTtlvTest extends AbstractTtlvSerializationTestSuite<SubmissionDate> {

    @Override
    public Class<SubmissionDate> type() {
        return SubmissionDate.class;
    }

    @Override
    public SubmissionDate createDefault() {
        return SubmissionDate.of(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC));
    }

    @Override
    public SubmissionDate createVariant() {
        return SubmissionDate.of(OffsetDateTime.of(2025, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC));
    }
}