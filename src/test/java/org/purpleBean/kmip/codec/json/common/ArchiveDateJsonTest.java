package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ArchiveDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ArchiveDate JSON Serialization Tests")
class ArchiveDateJsonTest extends AbstractJsonSerializationSuite<ArchiveDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<ArchiveDate> type() {
        return ArchiveDate.class;
    }

    @Override
    protected ArchiveDate createDefault() {
        // TODO: Update with actual default values for your dataType
        return ArchiveDate.builder().value(FIXED_TIME).build();
    }

    @Override
    protected ArchiveDate createVariant() {
        // TODO: Update with different values to test variations
        return ArchiveDate.builder().value(FIXED_TIME.plusDays(1)).build();
    }
}
