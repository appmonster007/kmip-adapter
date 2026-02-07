package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ArchiveDate;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ArchiveDate XML Serialization Tests")
class ArchiveDateXmlTest extends AbstractXmlSerializationTestSuite<ArchiveDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<ArchiveDate> type() {
        return ArchiveDate.class;
    }

    @Override
    protected ArchiveDate createDefault() {

        return ArchiveDate.builder().value(FIXED_TIME).build();
    }

    @Override
    protected ArchiveDate createVariant() {

        return ArchiveDate.builder().value(FIXED_TIME.plusDays(1)).build();
    }
}
