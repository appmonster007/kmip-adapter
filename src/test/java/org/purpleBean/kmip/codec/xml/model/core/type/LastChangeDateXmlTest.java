package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.LastChangeDate;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("LastChangeDate XML Serialization Tests")
class LastChangeDateXmlTest extends AbstractXmlSerializationTestSuite<LastChangeDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    public Class<LastChangeDate> type() {
        return LastChangeDate.class;
    }

    @Override
    public LastChangeDate createDefault() {

        return LastChangeDate.builder().value(FIXED_TIME).build();
    }

    @Override
    public LastChangeDate createVariant() {

        return LastChangeDate.builder().value(FIXED_TIME.plusDays(1)).build();
    }
}
