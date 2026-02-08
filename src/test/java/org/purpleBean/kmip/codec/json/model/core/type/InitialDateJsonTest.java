package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.InitialDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("InitialDate JSON Serialization Tests")
class InitialDateJsonTest extends AbstractJsonSerializationTestSuite<InitialDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    public Class<InitialDate> type() {
        return InitialDate.class;
    }

    @Override
    public InitialDate createDefault() {
        return InitialDate.builder()
                .value(FIXED_TIME)
                .build();
    }

    @Override
    public InitialDate createVariant() {
        return InitialDate.builder()
                .value(FIXED_TIME.plusDays(1))
                .build();
    }
}