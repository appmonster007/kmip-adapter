package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ArchiveDate;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ArchiveDate XML Serialization Tests")
class ArchiveDateXmlTest extends AbstractXmlSerializationSuite<ArchiveDate> {

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
