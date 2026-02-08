package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ProtectStopDate;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ProtectStopDate XML Serialization Tests")
class ProtectStopDateXmlTest extends AbstractXmlSerializationTestSuite<ProtectStopDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    public Class<ProtectStopDate> type() {
        return ProtectStopDate.class;
    }

    @Override
    public ProtectStopDate createDefault() {
        return ProtectStopDate.builder()
                .value(FIXED_TIME)
                .build();
    }

    @Override
    public ProtectStopDate createVariant() {
        return ProtectStopDate.builder()
                .value(FIXED_TIME.plusDays(1))
                .build();
    }
}