package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ArchiveDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ArchiveDate JSON Serialization Tests")
class ArchiveDateJsonTest extends AbstractJsonSerializationTestSuite<ArchiveDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    public Class<ArchiveDate> type() {
        return ArchiveDate.class;
    }

    @Override
    public ArchiveDate createDefault() {

        return ArchiveDate.builder().value(FIXED_TIME).build();
    }

    @Override
    public ArchiveDate createVariant() {

        return ArchiveDate.builder().value(FIXED_TIME.plusDays(1)).build();
    }
}
