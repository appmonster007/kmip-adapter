package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DeactivationDate;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("DeactivationDate TTLV Serialization Tests")
class DeactivationDateTtlvTest extends AbstractTtlvSerializationSuite<DeactivationDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<DeactivationDate> type() {
        return DeactivationDate.class;
    }

    @Override
    protected DeactivationDate createDefault() {
        return DeactivationDate.builder()
                .value(FIXED_TIME)
                .build();
    }

    @Override
    protected DeactivationDate createVariant() {
        return DeactivationDate.builder()
                .value(FIXED_TIME.plusDays(1))
                .build();
    }
}