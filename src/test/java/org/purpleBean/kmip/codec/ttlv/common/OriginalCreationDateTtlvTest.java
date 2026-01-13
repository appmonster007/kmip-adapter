package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.OriginalCreationDate;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("OriginalCreationDate TTLV Serialization Tests")
class OriginalCreationDateTtlvTest extends AbstractTtlvSerializationTestSuite<OriginalCreationDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<OriginalCreationDate> type() {
        return OriginalCreationDate.class;
    }

    @Override
    protected OriginalCreationDate createDefault() {
        return OriginalCreationDate.builder()
                .value(FIXED_TIME)
                .build();
    }

    @Override
    protected OriginalCreationDate createVariant() {
        return OriginalCreationDate.builder()
                .value(FIXED_TIME.plusDays(1))
                .build();
    }
}