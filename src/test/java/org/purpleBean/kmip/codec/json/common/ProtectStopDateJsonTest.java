package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ProtectStopDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ProtectStopDate JSON Serialization Tests")
class ProtectStopDateJsonTest extends AbstractJsonSerializationTestSuite<ProtectStopDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<ProtectStopDate> type() {
        return ProtectStopDate.class;
    }

    @Override
    protected ProtectStopDate createDefault() {
        return ProtectStopDate.builder()
                .value(FIXED_TIME)
                .build();
    }

    @Override
    protected ProtectStopDate createVariant() {
        return ProtectStopDate.builder()
                .value(FIXED_TIME.plusDays(1))
                .build();
    }
}