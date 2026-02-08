package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.OriginalCreationDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("OriginalCreationDate JSON Serialization Tests")
class OriginalCreationDateJsonTest extends AbstractJsonSerializationTestSuite<OriginalCreationDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    public Class<OriginalCreationDate> type() {
        return OriginalCreationDate.class;
    }

    @Override
    public OriginalCreationDate createDefault() {
        return OriginalCreationDate.builder()
                .value(FIXED_TIME)
                .build();
    }

    @Override
    public OriginalCreationDate createVariant() {
        return OriginalCreationDate.builder()
                .value(FIXED_TIME.plusDays(1))
                .build();
    }
}