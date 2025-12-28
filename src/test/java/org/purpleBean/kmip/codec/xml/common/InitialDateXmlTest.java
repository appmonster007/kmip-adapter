package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.InitialDate;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("InitialDate XML Serialization Tests")
class InitialDateXmlTest extends AbstractXmlSerializationSuite<InitialDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<InitialDate> type() {
        return InitialDate.class;
    }

    @Override
    protected InitialDate createDefault() {
        return InitialDate.builder()
                .value(FIXED_TIME)
                .build();
    }

    @Override
    protected InitialDate createVariant() {
        return InitialDate.builder()
                .value(FIXED_TIME.plusDays(1))
                .build();
    }
}